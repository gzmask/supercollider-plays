//server start
(
	s=Server.local.boot;
	//Server.internal.boot;
)

//make something happen
(
	"Hello SC!".postln;

	Event.default.play;

	{ SinOsc.ar(440, 0, 0.2) }.play(s);

	SynthDef("my_first_sine", { Out.ar(0, SinOsc.ar(440,0,1,SinOsc.ar(220,0,1,0))) }).play(s);

	SynthDef("my_first_sine", { Out.ar(0, SinOsc.ar(440,0,Line.kr( 1,0,2) ,SinOsc.ar(220,0,Line.kr( 1,0,2),0))) }).play(s);

	e = Env.linen(0.2,0.8,0.4,0.2); //attack decay sustain release envelope cycle
	SynthDef("my_first_sine", { Out.ar(0, SinOsc.ar(440,0,EnvGen.kr(e, 1),0)) }).play(s);
)

//set the adsr sound assign it to SynthDef
(
SynthDef("my_first_sine", { arg gate; 
   var env, amp;
   env = Env.adsr(0.2,0.8,0.2);
   amp = EnvGen.kr(env, gate);
   Out.ar(0, SinOsc.ar(440,0,amp,0))
 }).writeDefFile;
s.sendSynthDef("my_first_sine");
)

//use a.set 1 and 0 to represent keyboard on and off
(
a = Synth("my_first_sine");
a.set("gate", 1);
a.set("gate", 0);
)

//modulation failed to work
(
var env,amp,mod,max=2;
mod = SinOsc.kr(1,0,10,0);
Out.ar(0, SinOsc.ar(440+(mod*max),0,amp,0));
)


//server quit
(
	Server.local.quit;
	//Server.internal.quit;
)
