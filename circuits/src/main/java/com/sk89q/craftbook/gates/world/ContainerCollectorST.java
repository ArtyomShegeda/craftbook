package com.sk89q.craftbook.gates.world;

import org.bukkit.Server;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;
import com.sk89q.craftbook.ic.SelfTriggeredIC;

/**
 * @author Me4502
 */
public class ContainerCollectorST extends ContainerCollector implements SelfTriggeredIC {

    public ContainerCollectorST(Server server, Sign sign) {

        super(server, sign);
    }

    @Override
    public String getTitle() {

        return "Self-triggered Container Collector";
    }

    @Override
    public String getSignTitle() {

        return "ST CONT COLLECT";
    }

    @Override
    public void think(ChipState chip) {

        chip.setOutput(0, collect());
    }


    public static class Factory extends ContainerCollector.Factory {

        public Factory(Server server) {

            super(server);
        }

        @Override
        public IC create(Sign sign) {

            return new ContainerCollectorST(getServer(), sign);
        }
    }

    @Override
    public boolean isActive() {

        return true;
    }

    @Override
    public void trigger(ChipState chip) {

    }

    @Override
    public void unload() {

    }


}
