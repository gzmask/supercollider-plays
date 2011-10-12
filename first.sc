//server start
(
	s=Server.local.boot;
)

//make something happen
(
	"Hello SC!".postln;
	"Hello SC".speak;

	(4*2).postln;

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
max.postln;
mod.postln;
Out.ar(0, SinOsc.ar(440+(mod*max),0,amp,0));
)

//sc140
(
	{LocalOut.ar(a=CombN.ar(BPF.ar(LocalIn.ar(2)*7.5+Saw.ar([32,33],0.2),2**LFNoise0.kr(4/3,4)*300,0.1).distort,2,2,40));a}.play;
	{Splay.ar(Ringz.ar(Impulse.ar([2, 1, 4], [0.1, 0.11, 0.12]), [0.1, 0.1, 0.5])) * EnvGen.kr(Env([1, 1, 0], [120, 10]), doneAction: 2)}.play;
	play{({|k|({|i|y=SinOsc;y.ar(i*k*k,y.ar(i*k**i/[4,5])*Decay.kr(Dust.kr(1/4**i),y.ar(0.1)+1*k+i,k*999))}!8).product}!16).sum};//#supercollider	
)

(
	b=Buffer.read(s,"sounds/a11wlk01.wav");play{t=Impulse.kr(5);PlayBuf.ar(1,b,1,t,Demand.kr(t,0,Dseq(1e3*[103,41,162,15,141,52,124,190],4)))!2};	
)

(
	play{f=LocalIn.ar(2).tanh;k=Latch.kr(f[0].abs,Impulse.kr(1/4));LocalOut.ar(f+CombC.ar(Blip.ar([4,6],100*k+50,0.9),1,k*0.3,50*f));f}//44.1kHz
)

(
	f={|t|Pbind(\note,Pseq([-1,1,6,8,9,1,-1,8,6,1,9,8]+5,319),\dur,t)};Ptpar([0,f.(1/6),12,f.(0.1672)],1).play//#supercollider reich RT @earslap
)	

s.freeAll;

//server quit
(
	Server.local.quit;
)
