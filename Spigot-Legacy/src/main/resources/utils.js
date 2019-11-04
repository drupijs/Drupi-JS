var setTimeout = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    return server.getScheduler().scheduleSyncDelayedTask(plugin,runnable, delay);
}
var setAsyncTimeout = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    return server.getScheduler().scheduleAsyncDelayedTask(plugin,runnable, delay);
}

var setInterval = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    return server.getScheduler().scheduleSyncRepeatingTask(plugin,runnable, 0, delay);
}
var setAsyncInterval = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    return server.getScheduler().scheduleAsyncRepeatingTask(plugin,runnable, 0, delay);
}

function players(){
    return server.getOnlinePlayers().size();
}

function color(message){
    return manager.color(message);
}
