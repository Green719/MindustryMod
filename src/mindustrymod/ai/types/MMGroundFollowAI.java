package mindustrymod.ai.types;

import arc.math.*;
import mindustry.*;
import mindustry.ai.types.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;

import mindustry.ai.types.GroundAI;

public class MMGroundFollowAI extends GroundAI{
    public Teamc following;

    @Override
    public void updateMovement(){

        if(following != null){
            moveTo(
                    following,
                    (following instanceof Sized s ? s.hitSize()/2f : 0f)
                            + unit.hitSize/2f + 15f,
                    50f
            );
        }else if(target != null && unit.hasWeapons()){
            moveTo(target, unit.range());
        }else{
            super.updateMovement();
        }

        if(shouldFaceTarget()){
            unit.lookAt(target);
        }else if(following != null){
            unit.lookAt(following);
        }

        if(timer.get(timerTarget3, 30f)){
            following = Units.closest(
                    unit.team,
                    unit.x,
                    unit.y,
                    Math.max(unit.type.range, 160f),
                    u -> !u.dead()
                            && !u.isFlying()
                            && !(u.controller() instanceof MMGroundFollowAI)
                            && u.type != unit.type,
                    (u, tx, ty) -> -u.maxHealth + Mathf.dst2(u.x, u.y, tx, ty) / 6400f
            );
        }
    }

    public boolean shouldFaceTarget(){
        return target != null && (following == null || unit.within(target, unit.range()));
    }

    @Override
    public AIController fallback(){
        return new GroundAI();
    }

    @Override
    public boolean useFallback(){
        return Vars.state.rules.pvp || Vars.state.rules.waveTeam != unit.team;
    }
}