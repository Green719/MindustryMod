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
import mindustry.content.Fx;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

import mindustrymod.content.MMUnitTypes;

public class MMBlocks {
    public static Block 
                //payload things
                    airAssaultAssembler, mechSupportAssembler, 
                //tbd
                    serpuleanAssemblerModule, 
                //remade stuff
                    serpDeconstructor, serpConstructor,
                //turrets
                    cluster;

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

        cluster = new ItemTurret("cluster"){{
            requirements(Category.turret, with(Items.surgeAlloy, 200, Items.graphite, 400, Items.copper, 800, Items.plastanium, 400, Items.phaseFabric, 100, Items.thorium, 200));
            ammo(
                    Items.plastanium, new ArtilleryBulletType(2.7f, 300, "shell"){{
                        despawnEffect = hitEffect = new ExplosionEffect(){{
                            waveColor = Pal.plastaniumFront;
                            smokeColor = Color.gray;
                            sparkColor = Pal.plastaniumBack;
                            waveStroke = 4f;
                            waveRad = 54f;
                        }};
                        knockback = 1.6f;
                        lifetime = 136.3f;
                        width = 18f;
                        height = 24f;
                        hitSize = 10f;
                        buildingDamageMultiplier = 0.1f;

                        ammoMultiplier = 1f;
                        collidesTiles = true;
                        collidesAir = true;
                        collidesGround = true;
                        collides = false;

                        splashDamageRadius = 52f;
                        splashDamage = 350f;
                        scaledSplashDamage = true;

                        fragBullet = new ArtilleryBulletType(2.5f, 38, "shell"){{
                            width = 14f;
                            height = 20f;
                            hitSize = -1f;
                            lifetime = 25f;
                            backColor = Pal.plastaniumBack;
                            frontColor = Pal.plastaniumFront;
                            despawnEffect = hitEffect = new ExplosionEffect(){{
                                waveColor = Pal.plastaniumFront;
                                smokeColor = Color.gray;
                                sparkColor = Pal.plastaniumBack;
                                waveStroke = 4f;
                                waveRad = 28f;
                            }};

                            splashDamageRadius = 32f;
                            splashDamage = 35f;
                            buildingDamageMultiplier = 0.1f;

                            collidesTiles = true;
                            collidesAir = true;
                            collidesGround = true;
                            collides = false;

                            fragBullets = 6;
                            fragBullet = new BasicBulletType(2.1f, 20, "shell"){{
                                width = 12f;
                                height = 16f;
                                hitSize = 10f;
                                lifetime = 25f;
                                backColor = Pal.plastaniumBack;
                                frontColor = Pal.plastaniumFront;
                                despawnEffect = hitEffect = new ExplosionEffect(){{
                                    waveColor = Pal.plastaniumFront;
                                    smokeColor = Color.gray;
                                    sparkColor = Pal.plastaniumBack;
                                    waveStroke = 4f;
                                    waveRad = 30f;
                                }};

                                splashDamageRadius = 28f;
                                splashDamage = 55f;
                                buildingDamageMultiplier = 0.1f;

                                collidesTiles = true;
                                collidesAir = true;
                                collidesGround = true;
                            }};
                        }};
                        fragBullets = 8;

                        backColor = Pal.plastaniumBack;
                        frontColor = Pal.plastaniumFront;

                        fragAngle = 0f;
                        fragSpread = 45f;
                        fragRandomSpread = 10f;
                    }},
                    Items.surgeAlloy, new ArtilleryBulletType(2.7f, 350, "shell"){{
                        despawnEffect = hitEffect = new ExplosionEffect(){{
                            waveColor = Pal.surgeAmmoFront;
                            smokeColor = Color.gray;
                            sparkColor = Pal.surgeAmmoBack;
                            waveStroke = 4f;
                            waveRad = 54f;
                        }};
                        knockback = 1.6f;
                        lifetime = 136.3f;
                        width = 18f;
                        height = 24f;
                        hitSize = 10f;

                        ammoMultiplier = 1f;
                        collidesTiles = true;
                        collidesAir = true;
                        collidesGround = true;
                        collides = false;

                        splashDamageRadius = 52f;
                        splashDamage = 350f;
                        scaledSplashDamage = true;
                        buildingDamageMultiplier = 0.1f;

                        fragBullet = new ArtilleryBulletType(2.5f, 30, "shell"){{
                            width = 14f;
                            height = 20f;
                            hitSize = -1f;
                            lifetime = 25f;
                            backColor = Pal.surgeAmmoBack;
                            frontColor = Pal.surgeAmmoFront;
                            despawnEffect = hitEffect = new ExplosionEffect(){{
                                waveColor = Pal.surgeAmmoFront;
                                smokeColor = Color.gray;
                                sparkColor = Pal.surgeAmmoBack;
                                waveStroke = 4f;
                                waveRad = 32f;
                            }};

                            splashDamageRadius = 36f;
                            splashDamage = 25f;

                            lightning = 2;
                            lightningDamage = 26;
                            lightningLength = 12;

                            collidesTiles = true;
                            collidesAir = true;
                            collidesGround = true;
                            collides = false;

                            fragBullets = 6;
                            fragBullet = new BasicBulletType(2.1f, 20, "shell"){{
                                width = 12f;
                                height = 16f;
                                hitSize = 10f;
                                lifetime = 25f;
                                backColor = Pal.surgeAmmoBack;
                                frontColor = Pal.surgeAmmoFront;
                                despawnEffect = hitEffect = new ExplosionEffect(){{
                                    waveColor = Pal.surgeAmmoFront;
                                    smokeColor = Color.gray;
                                    sparkColor = Pal.surgeAmmoBack;
                                    waveStroke = 4f;
                                    waveRad = 30f;
                                }};

                                splashDamageRadius = 28f;
                                splashDamage = 65f;
                                buildingDamageMultiplier = 0.1f;

                                collidesTiles = true;
                                collidesAir = true;
                                collidesGround = true;

                                lightning = 2;
                                lightningDamage = 16;
                                lightningLength = 8;
                            }};
                        }};
                        fragBullets = 6;

                        lightning = 3;
                        lightningDamage = 26;
                        lightningLength = 16;

                        backColor = Pal.surgeAmmoBack;
                        frontColor = Pal.surgeAmmoFront;

                        fragAngle = 0f;
                        fragSpread = 45f;
                        fragRandomSpread = 10f;
                    }}
            );

            shoot.shots = 1;
            inaccuracy = 0f;
            reload = 330f;
            
            ammoPerShot = 10;
            rotateSpeed = 1.4f;

            ammoEjectBack = 5f;
            ammoUseEffect = Fx.casing3;
            ammoPerShot = 1;

            scaledHealth = 150;
            shootSound = Sounds.shootTank;

            coolant = consumeCoolant(0.5f);
            coolantMultiplier = 0.25f;
            size = 4;

            //to adjust

            range = 400f;
            minRange = 96f;

            recoil = 1.5f;
            shake = 6f;
            newTargetInterval = 50f;
            shootWarmupSpeed = 0.02f;
            warmupMaintainTime = 250f;

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-mid"){{
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    under = false;
                    moveY = -4.75f;
                    mirror = false;
                    heatColor = Pal.turretHeat;
                }});
            }};

        }};
    }
}