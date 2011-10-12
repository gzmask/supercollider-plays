Server.default = s = Server.internal.boot;

(
	{SinOsc.ar(LFNoise0.ar([10, 15], 400, 800), 0, 0.3)}.play;
)

(
	{RLPF.ar(LFSaw.ar([8, 12], 0, 0.2),LFNoise1.ar([2, 3].choose, 1500, 1600), 0.05,mul:0.4)}.play;
)

(
	{SinOsc.ar(abs(LFNoise0.ar(50, 400, 800)), 0, 0.3)}.play;
)

//SinOsc: freq, phase, amp(mul)
(
	{SinOsc.ar([500,440], 0, 1.0)}.play(1);
)

(
	{WhiteNoise.ar([0.2,0.7])}.play(1);
)

//mouse bind
( 
	{Out.ar(0, In.ar(MouseY.kr(15, 23).div(1), 1)*0.8)}.play; 
	{Out.ar(16, [SinOsc.ar, Saw.ar, Pulse.ar, LFTri.ar, LFNoise0.ar(200), Dust.ar(100), PinkNoise.ar, WhiteNoise.ar])}.play;
)

//phase cancellation
( 
	{ a = SinOsc.ar(400, mul: 0.3); 
	b = SinOsc.ar(400, MouseX.kr(0, 2pi), mul: 0.3); 
	[a + b, 0, a, b] }.play;
)

// 0 phase 
{SinOsc.ar(SinOsc.ar(0.3, 0, 7, 72).round(1).midicps, 0, 0.7)}.play
// 0.5pi (1/4) phase 
{SinOsc.ar(SinOsc.ar(0.3, 0.5pi, 7, 72).round(1).midicps, 0, 0.7)}.play
// 1pi (1/2) phase 
{SinOsc.ar(SinOsc.ar(0.3, 1pi, 7, 72).round(1).midicps, 0, 0.7)}.play
// 1.5pi (3/4) phase 
{SinOsc.ar(SinOsc.ar(0.3, 1.5pi, 7, 72).round(1).midicps, 0, 0.7)}.play


s.freeAll;

s.quit;
