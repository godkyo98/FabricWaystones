package wraith.waystones.block;

import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class WaystoneBlockEntityRenderer extends BlockEntityRenderer<WaystoneBlockEntity> {

    public WaystoneBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(WaystoneBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if(entity.getCachedState().get(WaystoneBlock.HALF) == DoubleBlockHalf.LOWER) return;
        ItemStack stack = new ItemStack(Items.ENDER_EYE);
        matrices.push();
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.translate(1f, 1.5f, 1f);
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(entity.getWorld().getTime() + tickDelta));
        int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.FIXED, lightAbove, overlay, matrices, vertexConsumers);
        matrices.pop();
    }

}
