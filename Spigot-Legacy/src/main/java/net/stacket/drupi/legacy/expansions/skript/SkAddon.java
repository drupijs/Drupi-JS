package net.stacket.drupi.legacy.expansions.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.ExpressionType;
import net.stacket.drupi.legacy.MainPlugin;
import net.stacket.drupi.legacy.expansions.skript.effects.effCallDrupiFunction;
import net.stacket.drupi.legacy.expansions.skript.expressions.expResultOfDrupiFunction;
import net.stacket.drupi.shared.api.Drupi;

public class SkAddon {

    public static SkriptAddon skaddon;
    public static Drupi drupi;

    public SkAddon(Drupi drupi){
        this.drupi = drupi;
        skaddon = Skript.registerAddon(MainPlugin.instance);

        Skript.registerEffect(effCallDrupiFunction.class, "call drupi function %string% [with [argument[s]] %objects%]");
        Skript.registerExpression(expResultOfDrupiFunction.class, Object.class, ExpressionType.SIMPLE,"result [of] drupi function %string% [with [argument[s]] %objects%]");
    }

}
