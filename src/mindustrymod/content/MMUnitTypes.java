package mindustrymod.content;

import arc.Core;
import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import arc.math.geom.Rect;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.FlyingAI;
import mindustry.ai.types.MinerAI;
import mindustry.audio.SoundLoop;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.*;
import mindustry.entities.units.WeaponMount;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.MultiPacker;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

import mindustry.ai.types.AssemblerAI;
import mindustrymod.ai.types.MMGroundFollowAI;

public class MMUnitTypes{

    public static UnitType nadir, glaive, serpens, altAssemblyDrone;

    public static void load() {
        nadir = new UnitType("nadir") {{

            constructor = UnitEntity::create;

            health = 2400;
            rotateSpeed = 1.4f;
            speed = 1.05f;
            accel = 0.09f;
            drag = 0.032f;
            flying = true;
            hitSize = 26f;
            lowAltitude = true;
            forceMultiTarget = true;
            armor = 9f;
            range = 244f;

            engineOffset = 15f;
            engineSize = 6.75f;
            ammoType = new ItemAmmoType(Items.graphite);

            weapons.add(new Weapon("artillery-mount") {{
                reload = 20f;
                x = 10f;
                y = -2f;
                top = true;
                rotate = true;
                rotateSpeed = 4f;
                shake = 1f;
                shoot.shots = 3;
                shoot.shotDelay = 1f;
                inaccuracy = 0f;
                velocityRnd = 0.1f;
                shootSound = Sounds.shootReign;
                shootY = 5.75f;
                layerOffset = 0.02f;

                showStatSprite = true;

                bullet = new FlakBulletType(4.5f, 24) {{
                    width = 8f;
                    height = 12f;
                    hitSize = 8f;
                    lifetime = 25f;

                    backColor = Pal.bulletYellowBack;
                    frontColor = Pal.bulletYellow;
                    hitEffect = Fx.hitScepterSecondary;
                    despawnEffect = Fx.hitScepterSecondary;

                    splashDamage = 10f;
                    splashDamageRadius = 5f;
                    collidesAir = true;
                }};
            }});

            weapons.add(new Weapon("mindustry-mod-nadir-mainArmanent") {{
                reload = 20f;
                x = 10f;
                y = 4f;
                rotate = true;
                shake = 1f;
                shoot.shots = 3;
                inaccuracy = 10f;
                shootSound = Sounds.shootRipple;
                rotateSpeed = 10f;
                layerOffset = -0.01f;

                shootStatusDuration = 120f;
                shootStatus = MMStatusEffects.firingSlow;
                shoot.firstShotDelay = 60f;

                bullet = new ArtilleryBulletType(2f, 20) {{
                    width = 8f;
                    height = 10f;
                    shrinkX = 0f;
                    shrinkY = 0f;
                    splashDamageRadius = 40f;
                    splashDamage = 26f;
                    lifetime = 120f;
                    trailColor = Pal.unitBack;
                    backColor = Pal.unitBack;
                    frontColor = Pal.unitFront;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;

                    layerOffset = -0.02f;
                    collidesTiles = false;
                }};
            }});
        }};

        glaive = new UnitType("glaive") {{

            constructor = MechUnit::create;

            flying = false;
            health = 900;
            rotateSpeed = 1.4f;
            speed = 0.5f;
            hitSize = 10f;
            forceMultiTarget = true;
            armor = 7f;

            weapons.add(new Weapon("missiles-mount") {{
                mirror = false;
                reload = 25f;
                x = 0f;
                y = -5f;
                rotate = true;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.shootMissileShort;

                bullet = new MissileBulletType(3.6f, 27, "missile") {{
                    width = 8f;
                    height = 8f;
                    shrinkY = 0f;
                    drag = -0.003f;
                    homingRange = 3f;

                    splashDamageRadius = 19f;
                    splashDamage = 18f;
                    lifetime = 50f;

                    trailColor = Color.gray;
                    backColor = Pal.bulletYellowBack;
                    frontColor = Pal.bulletYellow;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;

                    weaveScale = 8f;
                    weaveMag = 2f;
                }};
            }});

            weapons.add(new Weapon("mindustry-mod-glaive-gun") {{
                mirror = true;
                reload = 20f;
                x = 8f;
                rotate = false;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.shootCyclone;
                top = false;

                bullet = new FlakBulletType(3.6f, 24) {{
                    shootEffect = Fx.shootSmall;
                    width = 6f;
                    height = 11f;
                    hitEffect = Fx.flakExplosion;
                    splashDamage = 10f;
                    splashDamageRadius = 25f;
                    lifetime = 50f;

                    fragBullets = 4;
                    explodeRange = 30f;
                    collidesGround = true;

                    backColor = hitColor = trailColor = Pal.bulletYellowBack;
                    frontColor = Pal.bulletYellow;
                    despawnEffect = Fx.hitBulletColor;

                    fragBullet = new BasicBulletType(3f, 9, "bullet") {{
                        width = 5f;
                        height = 12f;
                        shrinkY = 1f;
                        lifetime = 12f;
                        backColor = Pal.bulletYellow;
                        frontColor = Pal.bulletYellowBack;
                        despawnEffect = Fx.none;
                    }};
                }};
            }});
        }};

        serpens = new UnitType("serpens") {{

            constructor = MechUnit::create;
            aiController = MMGroundFollowAI::new;

            flying = false;
            health = 2700;
            rotateSpeed = 1.4f;
            speed = 0.8f;
            hitSize = 18f;
            forceMultiTarget = true;
            armor = 14f;
            range = 160f;

            targetGround = true;
            targetAir = false;

            weapons.add(new RepairBeamWeapon("repair-beam-weapon-center-large") {{
                y = 6f;
                top = true;
                rotate = false;
                mirror = false;
                shootY = 6f;
                beamWidth = 0.8f;
                repairSpeed = 2.0f;

                bullet = new BulletType() {{
                    maxRange = 160f;
                }};
            }});

            weapons.add(new RepairBeamWeapon("repair-beam-weapon-center-large") {{
                x = 10f;
                y = -6f;
                shootY = 6f;
                beamWidth = 0.8f;
                repairSpeed = 1.0f;

                bullet = new BulletType() {{
                    maxRange = 120f;
                }};
            }});

            weapons.add(new PointDefenseWeapon("mindustry-mod-serpens-pointDefense") {{
                y = 0f;
                shootY = 4f;
                reload = 6f;
                mirror = false;

                targetInterval = 6f;
                targetSwitchInterval = 9f;
                recoil = 0.2f;

                bullet = new BulletType() {{
                    shootSound = Sounds.shootLaser;
                    shootEffect = Fx.sparkShoot;
                    hitEffect = Fx.pointHit;
                    maxRange = 120f;
                    damage = 25f;
                }};
            }});
        }};

        altAssemblyDrone = new UnitType("altAssemblyDrone"){{
            controller = u -> new AssemblerAI();
            constructor = BuildingTetherPayloadUnit::create;

            flying = true;
            drag = 0.06f;
            accel = 0.11f;
            speed = 1.3f;
            health = 120;
            engineSize = 2f;
            engineOffset = 5.5f;
            payloadCapacity = 0f;
            targetable = false;
            bounded = false;

            isEnemy = false;
            hidden = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            envEnabled = Env.any;
            envDisabled = Env.none;
        }};
    }
}
