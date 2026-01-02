package mindustrymod.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import mindustry.content.Items;
import mindustry.content.UnitTypes;
import mindustry.content.Blocks;
import mindustry.content.Liquids;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

import mindustrymod.content.MMUnitTypes;

public class MMBlocks {
    public static Block airAssaultAssembler, mechSupportAssembler, serpuleanAssemblerModule, serpDeconstructor, serpConstructor;

    public static void load() {
        airAssaultAssembler = new UnitAssembler("airAssaultAssembler") {{
            requirements(Category.units, with(Items.lead, 1200, Items.titanium, 250, Items.plastanium, 70, Items.silicon, 650));
            regionSuffix = "-dark";
            size = 5;
            plans.add(
                    new AssemblerUnitPlan(MMUnitTypes.nadir, 60f * 45f, PayloadStack.list(UnitTypes.horizon, 4, Blocks.plastaniumWallLarge, 6))
            );
            droneType = MMUnitTypes.altAssemblyDrone;
            areaSize = 8;

            consumePower(2.5f);
            consumeLiquid(Liquids.oil, 10f / 60f);
        }};

        mechSupportAssembler = new UnitAssembler("mechSupportAssembler") {{
            requirements(Category.units, with(Items.lead, 1000, Items.titanium, 450, Items.plastanium, 150, Items.silicon, 650, Items.phaseFabric, 50));
            regionSuffix = "-dark";
            size = 5;
            plans.add(
                    new AssemblerUnitPlan(MMUnitTypes.serpens, 60f * 75f, PayloadStack.list(UnitTypes.pulsar, 4, Blocks.phaseWallLarge, 2))
            );
            droneType = MMUnitTypes.altAssemblyDrone;
            areaSize = 8;

            consumePower(2.5f);
            consumeLiquid(Liquids.cryofluid, 12f / 60f);
        }};
        //tb used later
        //serpuleanAssemblerModule = new UnitAssemblerModule("serpulean-assembler-module") {{
            //requirements(Category.units, with(Items.lead, 1500, Items.thorium, 550, Items.phaseFabric, 200, Items.surgeAlloy, 150, Items.plastanium, 400));
            //consumePower(3.5f);
            //regionSuffix = "-dark";

            //size = 5;
        //}};
        
        serpDeconstructor = new PayloadDeconstructor("serpDeconstructor"){{
            requirements(Category.units, with(Items.silicon, 250, Items.titanium, 150, Items.lead, 300, Items.graphite, 90));
            regionSuffix = "-dark";
            itemCapacity = 250;
            consumePower(3f);
            size = 5;
            deconstructSpeed = 6f;
        }};

        serpConstructor = new Constructor("serpConstructor"){{
            requirements(Category.units, with(Items.silicon, 150, Items.titanium, 80, Items.lead, 200));
            regionSuffix = "-dark";
            hasPower = true;
            buildSpeed = 0.6f;
            consumePower(2.5f);
            size = 3;
            filter = Seq.with(Blocks.scrapWallLarge, Blocks.plastaniumWallLarge, Blocks.copperWallLarge, Blocks.titaniumWallLarge, Blocks.thoriumWallLarge, Blocks.surgeWallLarge, Blocks.liquidContainer, Blocks.container, Blocks.phaseWallLarge);
        }};
    }
}