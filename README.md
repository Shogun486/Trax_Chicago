Trax Chicago (Live-Tracking for CTA 'L' Trains)
==========
If you want to only see a demo, you can check it out here.

This Android app (programmed in Java) visualizes en route trains' distance from a station based on approximate arrival times provided by the CTA's API. The CTA API team is actually working on creating a live-tracking API of its own, but until that is released, I thought of implementing something similar along those lines.
In this app, the user can view all 'L' stations in Chicagoland, what rail colors they serve, the stops/branches at each station, and the arrival times for each respective run
for the next hour.

## What problems does this app solve and for whom?
Most train passengers find themselves leaning towards the train tracks to look down the subway/elevated paths and hope to see a train's lights arriving at their station. This is a natural human tendency, as people want to know when their trains are going to arrive as soon as possible. However, approaching train tracks is dangerous. Of course, there already exists a solution to this problem, and that would be the estimated arrival times displayed on the big screen at each station. But how about a mini-visualizer that shows you a countdown timer along with a dynamic representation of a train nearing you as the clock ticks? This would be a feature I would like to see implemented at train stations in addition to the expected arrival times. Therefore, I created this app to achieve just that.

## What's unique about this app?
I've noticed on other train-tracking mobile applications that a visualizer is not common, and that they only the show arrival times (not implemented as a countdown whatsoever). 
This mobile app, in addition to the countdown and visualzier, has a user interface (UI) that mimics design schemes on train platforms. In my demo, you can see that the color-coding arrangement allows for easy navigation, but also enhances the user experience by making the UI look more like real signs on a platform.

## What's next?
Since the CTA API team is actually working on creating a live-tracking API of its own, until then I plan on adding more features to this app and making it even better. In the meantime, if you would like to use this app: 

Simply install Android Studio --> Build Clean Project --> Run on an emulator
(this app has been tested on multiple devices with multiple screen sizes,
so feel free to run the app on any device you choose).
