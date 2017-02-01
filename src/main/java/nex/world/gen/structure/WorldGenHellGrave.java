/*
 * NetherEx
 * Copyright (c) 2016-2017 by LogicTechCorp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nex.world.gen.structure;

import com.google.common.collect.Lists;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;
import nex.util.WeightedUtil;
import nex.util.WorldGenUtil;

import java.util.List;
import java.util.Random;

public class WorldGenHellGrave extends WorldGenerator
{
    private List<WeightedUtil.NamedItem> variants = Lists.newArrayList(
            new WeightedUtil.NamedItem("single_empty", 4),
            new WeightedUtil.NamedItem("single_chest", 3),
            new WeightedUtil.NamedItem("single_fancy", 2),
            new WeightedUtil.NamedItem("group_basic", 1)
    );

    @Override
    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
        rand = world.getChunkFromBlockCoords(blockPos).getRandomWithSeed(world.getSeed());

        Mirror[] mirrors = Mirror.values();
        Rotation[] rotations = Rotation.values();
        Mirror mirror = mirrors[rand.nextInt(mirrors.length)];
        Rotation rotation = rotations[rand.nextInt(rotations.length)];
        MinecraftServer server = world.getMinecraftServer();
        TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
        Template template = manager.getTemplate(server, WeightedUtil.getRandomStructure(rand, variants, "grave_hell_"));

        ChunkPos chunkPos = new ChunkPos(blockPos);
        StructureBoundingBox structureBB = new StructureBoundingBox(chunkPos.getXStart(), 0, chunkPos.getZStart(), chunkPos.getXEnd(), 256, chunkPos.getZEnd());
        PlacementSettings settings = new PlacementSettings().setMirror(mirror).setRotation(rotation).setBoundingBox(structureBB).setRandom(rand);
        BlockPos structureSize = Template.transformedBlockPos(settings.copy(), template.getSize());
        BlockPos spawnPos = WorldGenUtil.getSuitableHeight(world, template.getZeroPositionWithTransform(new BlockPos(chunkPos.getXStart() + 8 - structureSize.getX() / 2, 96, chunkPos.getZStart() + 8 - structureSize.getZ() / 2), mirror, rotation), structureSize.getX(), structureSize.getZ()).down();

        if(spawnPos != BlockPos.ORIGIN)
        {
            template.addBlocksToWorld(world, spawnPos, settings.copy(), 20);
            WorldGenUtil.setChestContents(world, rand, spawnPos, structureSize, LootTableList.CHESTS_NETHER_BRIDGE);
            return true;
        }
        return false;
    }
}