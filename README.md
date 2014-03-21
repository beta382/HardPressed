#HardPressed - A Letterpress tool

##What is this?

This is a `Java` application I wrote a year or so back so that I could beat my word-savvy girlfriend at Letterpress (Pit
a programmer against a word-smart opponent in a word game, and the programmer will eventually make something to win for
him).

I wasn't satisfied with various "word-search" tools I found floating around the Internet, mainly because everything I
found only accomplished one of the two purposes I wanted. Either the tool would give me all of the words you can 
possibly form from a given set of letters, or all of the words who contain all of the letters you provide. I wanted
both. Thus, HardPressed was created. You give it all of your letters, it gives you all the words you can make with
those. You can then give it the letters you specifically want to use all of, and it gives you all the words from the
list of playable words that use all of those letters (Letterpress is territorial, so this was a big deal to me; other
tools forced me to go through a massive list looking for a word that would do me any good, this tool does the work
for me)

- 99% guaranteed to let you totally pwn friends/enemies/total strangers at Letterpress
- Not a "cheat engine", but rather, a "tool" or "resource" (cheaters never win, but those that utilize their resources 
tend to fare best)
- That means you can use this without feeling guilty, seriously
- Written in `Java`, so it should just work anywhere you have the `JRE` installed (might only run out of the box with
`Java 7` or later, I don't remember what all I set when I packaged this, however, if you add the code to your own
workspace and build it yourself, it probably is backwards compatible with `Java 6` and prior)



##Setup

- Have `JRE 7` or greater installed on your machine and download the [latest release](https://github.com/beta382/HardPressed/releases), **or**
- Have `JDK 6` or greater installed on your machine (might even be further backwards compatible, IDK, I only tested building with as low a `Java 6`), add [the code](https://github.com/beta382/HardPressed/tree/master/HardPressed/src/com/beta382/hardpressed) to your 
own project, and build it yourself

##Usage

1. Under `All available letters:`, type all 25 letters on your board (if you manage to muck this up, it will yell at 
you, telling you how you mucked it up).
2. Under `Preferred letters:`, choose from the letters on your board the ones you "really really really" want to play
(again, if you manage to muck this up, it will yell at you, telling you how you mucked it up).
3. Click `Search`, and you will be presented with a beautiful list, sorted by length descending.
4. If you don't get any results, remove some letters from `Preferred letters:`, and click `Search` again (repeat until
you get results).
5. (optional) Use the sort buttons at the top to change the order of the results, in case you want to give your opponent
some slack, or make your resourcefulness less apparent.
6. Pwn your opponent with the masterful words you are presented with.
7. Repeat next turn.
8. ???
9. Profit.
