package be.webtechie.crac.javafx.example.ui;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;

import java.util.Locale;
import java.util.Random;

public class MainWindow extends HBox implements Resource {

    private static final Logger LOGGER = LogManager.getLogger(MainWindow.class);

    private static final int TILE_WIDTH = 300;
    private static final int TILE_HEIGHT = 300;

    private final Random random;
    private final Tile clock;
    private final Tile gauge;

    public MainWindow() {
        Core.getGlobalContext().register(this);

        this.setSpacing(25);

        random = new Random();

        clock = TileBuilder.create()
                .skinType(SkinType.CLOCK)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("Clock")
                .dateVisible(true)
                .locale(Locale.ENGLISH)
                .running(true)
                .build();

        gauge = TileBuilder.create()
                .skinType(SkinType.GAUGE)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("Gauge Tile")
                .unit("V")
                .threshold(400)
                .minValue(0)
                .maxValue(500)
                .build();

        this.getChildren().addAll(clock, gauge);
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) {
        LOGGER.info("beforeCheckpoint was called");
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) {
        LOGGER.info("afterRestore was called");
    }

    public void setRandomValue() {
        try {
            var value = random.nextInt(((int) gauge.getMaxValue() - (int) gauge.getMinValue()) + 1) + gauge.getMinValue();
            LOGGER.info("Setting gauge to " + value);
            gauge.setValue(value);
            clock.setText("New value is " + value);
        } catch (Exception ex) {
            LOGGER.error("Can't set the random value: " + ex.getMessage());
        }
    }
}
