package noobanidus.mods.brh;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("brh")
public class BiggerHitboxes {
  public static final Logger LOG = LogManager.getLogger();
  public static final String MODID = "brh";

  public BiggerHitboxes() {
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigManager.COMMON_CONFIG);
    ConfigManager.loadConfig(ConfigManager.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("brh-common.toml"));

    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

    modBus.addListener(this::onLoadComplete);
    modBus.addListener(this::onConfigChange);
  }

  public void onLoadComplete (FMLLoadCompleteEvent event) {
    EntityType.RABBIT.size = EntitySize.flexible((float) (double) ConfigManager.WIDTH.get(), (float) (double) ConfigManager.HEIGHT.get());
  }

  public void onConfigChange (ModConfig.Reloading event) {
    if (event.getConfig().getType() == ModConfig.Type.COMMON) {
      ConfigManager.COMMON_CONFIG.setConfig(event.getConfig().getConfigData());
      onLoadComplete(null);
    }
  }
}
