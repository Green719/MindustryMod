package mindustrymod;

import arc.Core;
import arc.Events;
import arc.graphics.Color;
import arc.util.*;
import arc.util.serialization.Jval;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.gen.Call;
import mindustry.gen.Icon;
import mindustry.gen.Player;
import mindustry.graphics.Pal;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
import mindustry.net.ServerGroup;
import mindustry.ui.Links;
import mindustry.ui.Styles;
import mindustry.ui.WarningBar;
import mindustry.ui.dialogs.BaseDialog;

import mindustrymod.content.MMUnitTypes;
import mindustrymod.content.MMStatusEffects;
import mindustrymod.content.MMBlocks;

public class MindustryMod extends Mod{

    public MindustryMod(){
    }

    @Override
    public void loadContent(){
        MMStatusEffects.load();
        MMUnitTypes.load();
        MMBlocks.load();
    }
}