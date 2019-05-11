package hundeklemmen.legacy.expansions.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.lang.ExpressionType;
import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.legacy.expansions.skript.effects.effCallDrupiFunction;
import hundeklemmen.legacy.expansions.skript.expressions.expResultOfDrupiFunction;
import hundeklemmen.shared.api.Drupi;

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
