package com.sk89q.craftbook.gates.world;

import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

import com.sk89q.craftbook.ic.AbstractIC;
import com.sk89q.craftbook.ic.AbstractICFactory;
import com.sk89q.craftbook.ic.ChipState;
import com.sk89q.craftbook.ic.IC;
import com.sk89q.craftbook.ic.ICUtil;
import com.sk89q.craftbook.ic.ICVerificationException;
import com.sk89q.craftbook.ic.RestrictedIC;

/**
 * @author Silthus
 */
public class PowerSensor extends AbstractIC {


    private Block center;

    public PowerSensor(Server server, Sign block) {

        super(server, block);
        load();
    }

    private void load() {

        try {
            center = ICUtil.parseBlockLocation(getSign());
        }
        catch(Exception e){}
    }

    @Override
    public String getTitle() {

        return "Power Sensor";
    }

    @Override
    public String getSignTitle() {

        return "POWER SENSOR";
    }

    @Override
    public void trigger(ChipState chip) {

        if (chip.getInput(0)) {
            chip.setOutput(0, isPowered());
        }
    }

    protected boolean isPowered() {

        return center.isBlockPowered() || center.isBlockIndirectlyPowered();
    }

    public static class Factory extends AbstractICFactory implements RestrictedIC {

        public Factory(Server server) {

            super(server);
        }

        @Override
        public IC create(Sign sign) {

            return new PowerSensor(getServer(), sign);
        }

        @Override
        public void verify(Sign sign) throws ICVerificationException {

            ICUtil.verifySignSyntax(sign);
        }
    }
}
