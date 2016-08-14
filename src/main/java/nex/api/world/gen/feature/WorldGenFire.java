package nex.api.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFire extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for(int i = 0; i < 64; ++i)
        {
            BlockPos newPos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            Block block = world.getBlockState(newPos.down()).getBlock();
            Material material = block.getMaterial(null);

            if(world.isAirBlock(newPos) && block.isFlammable(world, newPos.down(), EnumFacing.UP) && material.isSolid() && material != Material.ICE && material != Material.PACKED_ICE)
            {
                world.setBlockState(newPos, Blocks.FIRE.getDefaultState(), 2);
            }
        }

        return true;
    }
}