This project was somehow started on Thu 16 Jan 2020, for full articles, docs, pictures, diagrams see [mwiacek.com](http://www.mwiacek.com)

**It's not related to Mozilla anymore, it's just changed fork, where all Mozilla related branding will be removed before first official release.**

Created THX to AMD, Ubuntu and hard work.

# Why it was started

Currently the most popular browser on the market is Google Chrome.

I was sending various bug information there (see [list visible externally](https://bugs.chromium.org/p/chromium/issues/list?can=1&q=mwiacek&sort=-modified)), unfortunately some of them were not resolved over months or people had problems with understanding them.

I have also seen, that some decisions were definitely not-user friendly, for example:

* [Removing Jumbo support](https://groups.google.com/a/chromium.org/forum/#!topic/chromium-dev/DP9TQszzQLI) - this was done without checking effort on community and natural Earth environment + increased dramatically compilation time (for example from 1h to 4h), additionally tightened compilation with Google servers

* [No implementation for keydown and keyup](https://bugs.chromium.org/p/chromium/issues/detail?id=118639) - it was mentioned by many people, but Google refused for providing it

* [Suspending small change in history window helping dramatically in finding site](https://bugs.chromium.org/p/chromium/issues/detail?id=962309) - I proposed it somehow in May 2019, but Google decided to put it for some future

There are many smart SWE (SoftWare Engineers) in Google, but they're many times limited by schedule or doing more important tasks (from company point of view of course).

During months I was observing, that people doing great work with me are leaving project, also there were very controversial changes with API for external ad blockers, filesystem API and in the Dec 2019 there was seen (another) quality issue - [Chrome was ignoring or even loosing some user data](https://bugs.chromium.org/p/chromium/issues/detail?id=1033655).

It looks for me, that project is so big and popular now, that probably nobody see need of further investing in big team. This scenario looks a little bit like with Internet Explorer.

Other companies (like Microsoft) are taking code and adding brand, also there are many forks from people, who don't have too big resources.

**This is catastrophic - various updates are not going back to main tree.**

This was bad for me and in January 2020 I started looking into the code of last Chrome alternative, which are Mozilla products. Fundation is known in last years from very controversial steps like changing CEO, dropping some API with Firefox 57, firing technical people and some other things, but I was very positive after seeing Firefox Preview for Android.

Unfortunately I have realized, that some of my issues or suggestions from Oct or Nov 2019 were not touched for longer time.

There was visible first red light in my head, when I saw information on GitHub, that team is small and can have resources problem.

**If Mozilla want to attract people, it should never happen!** - mobile team should have resources + instead of few mobile projects there must be one and only one.

I decided to prepare trivial pieces of code, but they were dropped with information, that team has got own schedule and they know better, what is required.

**There were even doubts with 2-lines long change!**

I was very surprised with reactions + have seen, that even lack of JetStream 2 benchmark support (probably one of more popular) is not problem. I reported this last thing and about after three months it was pointed, that Preview doesn't have very small piece of code (it wasn't added later).

This was shock for me, because it looks, that there is big gap between manifest and reality (foundation works in really formal corporate way and technical works seems to be replaced by political correctness) + even example browsers from Mozilla have more advanced code in basic area than Preview.

They have quite good codebase, but it looks for me currently, that Preview is done with changed design guidlines in "a little bit" random way OR this is infitine research project (like Servo).

**You should never build house from top, if you want to have good results!**

In this case: if Preview is replacement for "old" mobile Firefox, it should first concentrate on basic functionalities like working with all sites, then add more basic stuff and then think about bells-and-whistles.

# What it is for

This project is my big research project, where I want to see, if I can prepare better Firefox (faster, smarter, with better interface) than foundation people.

My short term goals (as of 16 Jan 2020):

1. preparing repository, where I will have code cleaned from old parts with all elements required for local building (for now this is fork of fenix + android-components, I plan more)

2. making GUI interface extremly logical - cleaning it from very annoying "hey, this is Firefox" reminders, replacing collections with normal bookmarks folders structure, etc. **Believe me, number of issues is so huge, that I was so surprised, that somebody went into this direction.**

3. adding missed basic functionalities - support for handling long-running scripts, refresh gesture, local backup/restore for bookmarks, etc.

4. cleaning from all components making it slower - telemetry, etc. (and checking how fast it is after all)

5. removing Mozilla branding and putting app into Google Play

6. making regular sync with most important Firefox Preview functionalities (like support for plugins)

Long term goals (as of 16 Jan 2020):

1. getting more people able to help

2. working on more deep components and start cleaning mess, which I see already even after short code looking

3. adding Tor capabilities?

4. removing all this political correctness from code and made it making what it should do

# This is crazyness - too much

I have heard it many times.

In 1999 first I made fork of big project Gnokii, then started creating with big success something from scratch (1999-2007, other people are updating it till now).

Around 2011 I have started playing with Android and created big "Przepisy Drogowe" family, which is used for now.

In my "main" work I have also done a lot of coding.

Browser is of course much bigger, but I'm also much older + I need some fun for longer. Maybe my work will need year or years, but I will do definitely everything not to make it worse than Mozilla's code.

# This is not Google and this is so sloooowwwww

Also all "Chrome" like projects are already on the market and there are people working on them. Mozilla foundation is making very strange polital moves, but her code is so clean and good, that can provide some codebase for something. World needs some alternative, let's see, where this will be in half year and year.

Mozilla was making strange technical moves in last two or three years, let's code and show them what can be done.

# You're stupid man

Maybe, but (will) also get a lot of professional experience with this (and fun)

# You're laying. Mozilla/Google are great

Yes, they're. They changed world and **if in some place I said something incorrect about working there people, please take apologies** (I just wanted to show some aspects of their actions and there was nothing personal in this).

The problem is, that every revolution eats own children and with both companies I see only one sentence, which is "earning money".

Steven King said in January 2020 "...I would never consider diversity in matters of art. Only quality. It seems to me that to do otherwise would be wrong."

I will say - I will never consider other things than quality.

Maybe my research project will be only research project, but maybe also inspire others for good changes. Good makes good.

# I want to do something here

I don't care if you're politically correct or not, what you're doing in your private life as long as you can contribute and won't do something prohibited by GitHub, law and human conscience (no adverts for drags, no threats, etc.)

I will not pay for this work, I will not also say anything about making safe Internet, saving world from Google or other bullshit.

Let's do technical work together without unnecessary words - if it gives profits, it will be accepted.

# How to start

1. Download true-android-components (into true-android-components folder) and true-fenix (into true-fenix folder)

2. import true-android-components into Android Studio and wait for synchronization

3. import true-fenix into Android Studio and wait for creating local.properties file

4. in the true-fenix\local.properties add line ``autoPublish.android-components.dir=../true-android-components``

5. make again synchronization for true-fenix (maybe you have close and open project)
