var setTimeout = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    server.getScheduler().scheduleSyncDelayedTask(plugin,runnable, delay);
}
var setAsyncTimeout = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    server.getScheduler().scheduleAsyncDelayedTask(plugin,runnable, delay);
}

var setInterval = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    server.getScheduler().scheduleSyncRepeatingTask(plugin,runnable, 0, delay);
}
var setAsyncScInterval = function (fn,delay) {
    var runnable = new java.lang.Runnable({
        run: fn
    });
    server.getScheduler().scheduleAsyncRepeatingTask(plugin,runnable, 0, delay);
}