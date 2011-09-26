//server start
(
	Server.local.boot;
	Server.internal.boot;
)

//make something happen
(
	"Hello Radiant!".postln;
	"Hello SC!".postln;
	Event.default.play;
	{ SinOsc.ar(440, 0, 0.2) }.play;
)

//server quit
(
	Server.local.quit;
	Server.internal.quit;
)
