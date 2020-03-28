package noobanidus.mods.brh;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ConfigManager {
  private static ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

  public static ForgeConfigSpec COMMON_CONFIG;
  public static ForgeConfigSpec.DoubleValue WIDTH;
  public static ForgeConfigSpec.DoubleValue HEIGHT;
  public static ForgeConfigSpec.BooleanValue SILVERFISH;
  public static ForgeConfigSpec.DoubleValue SILVERFISH_WIDTH;
  public static ForgeConfigSpec.DoubleValue SILVERFISH_HEIGHT;

  static {
    COMMON_BUILDER.push("rabbit");
    WIDTH = COMMON_BUILDER.comment("width of the rabbit hitbox").defineInRange("width", 0.8, 0.0, 10.0);
    HEIGHT = COMMON_BUILDER.comment("height of the rabbit hitbox").defineInRange("height", 1.0, 0.0, 10.0);
    COMMON_BUILDER.pop();
    COMMON_BUILDER.push("silverfish");
    SILVERFISH_WIDTH = COMMON_BUILDER.comment("width of the silverfish hitbox").defineInRange("width", 1.0, 0.0, 10.0);
    SILVERFISH_HEIGHT = COMMON_BUILDER.comment("height of the silverfish hitbox").defineInRange("height", 1.0, 0.0, 10.0);
    COMMON_BUILDER.pop();

    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
    configData.load();
    spec.setConfig(configData);
  }
}
