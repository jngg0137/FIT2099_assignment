# FIT2099 Assignment (Semester 2, 2024)

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```

Assignments' Contribution Log:
https://docs.google.com/spreadsheets/d/1d_Xja7rJqmKZE3glNwH7M5D653KzUAg_YS8kBbL_Hzs/edit?usp=sharing

As per Adrian Kristanto's recommendation, only requirement 4+ was implemented due to time constraints. Base requirement
4 listed in "Requirement 4 features" wasn't implemented.
--------------------------------------------------------------------------------------------------------
Requirement 4+ features:
Crossbow(🏹) ranged weapon
- Fire crossbow bolts with a 50% chance to hit
- Different crossbow bolts to fire:
    - Fire BurningBolt (Ý) for 80 damage and ignites the surrounding ground it strikes, setting it on fire
      for 40 turns
    - Fire PoisonBolt (¥) for 80 damage and inflict the 'Poison' status effect for 20 turns, can be picked 
    up if it collides with Wall (#)
    - Fire Bolt(y) for 100 damage can be picked up if it collides with Wall (#)
      
________________________________________________________________________________________________________
Requirement 4 features: (not implemented)
A boss fight that is only accessible after interacting with the Suspicious Trader (ඞ) once all remembrances have been
traded in. Suspicious Trader (ඞ) will request help with getting back his ship after it was stolen by Mantis thief,
KazaaakplethKilik (Ÿ). The Locked Gate (×) at the north of Gravesite Plain will turn into a normal gate and
the player can access it to go to the Ruined Pier, where KazaaakplethKilik (Ÿ) can be found.

KazaaakplethKilik (Ÿ) has the following:
- 8000 HP
- Immunity to drowning
- WornShield (🛡️) weapon
- Crossbow(🏹) ranged weapon

KazaaakplethKilik (Ÿ) has the following attacks / actions:
- Move towards player
- Load crossbow with any bolts / look for bolt to pickup
- Use a turn to unsheathe shield and charge in player direction when player is in line of sight. Console will notify
  players of this to 'telegraph' the attack. When charging, he will move >= 2 tiles per tick (subject to change) in a
  straight line. If:
    - he collides with a Wall (#), he will be stunned for 2 turns.
    - he collides with another actor, he is stunned for 2 turns, with KazaaakplethKilik (Ÿ) stopping where he collided with
      the actor, and the actor being knocked back 2 tiles and taking 30 damage.
    - he falls into Deep Water (^), nothing happens
      After that happens, he will use a turn to sheathe the shield.
- When nearby player, slash with Mantis Blade for 80 damage. This attack has a 40% chance to hit and inflicts
  the 'Bleed' status effect for 5 turns.
- Fire crossbow bolts at player with 50% chance to hit (also telegraphed), it continues flying towards a ground it can't
  pass through if it misses.
    - Fire BurningBolt (Ý) at the player for 50 damage and ignites the surrounding ground it strikes, setting it on fire
      for 40 turns
    - Fire PoisonBolt (¥) at the player for 40 damage and inflict the 'Poison' status effect for 3 turns, can be picked up
      if it collides with Wall (#)
    - Fire Bolt(y) at the player for 20 damage and push them 2 tiles back, can be picked up if it collides with Wall (#)
- These chances are rolled once the player enters the Ruined Pier map / after each attack.
    - 25% chance for him to follow and slash the player once, 25% chance for him to use shield dash / crossbow.
      25% chance for him to move towards and pickup dropped crossbow bolts. 25% chance for him to taunt the player.
    - Whether he uses shield dash or crossbow depends on 50% chance / shield or crossbow bolts exist in inventory

New ground:
- There is Deep Water (^) surrounding the pier. If an actor moves onto it without drown immunity, they die instantly.
  Said actor can't move onto it intentionally. Items that fall into it are removed instantly.
- Plank (¦) being the floor of the pier. Is flammable.

New items:
- WornShield (🛡️) that reduces wielder's damage taken by 30% and allows them to charge in a direction. Has limited
  durability and can break, removing it from actor inventory.
- BurningBolt (Ý)
- PoisonBolt (¥)
- Bolt (y)

Ruined Pier map:
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
¦¦###¦¦¦¦¦¦########¦¦######¦¦¦¦¦¦¦¦¦¦^^^^^^^^^^^^^^
¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦##^^^^^^^^^^
¦¦¦¦¦###¦¦¦¦¦¦¦¦¦¦###¦##¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦#####¦¦¦¦^^
¦¦¦¦¦^^#¦¦¦¦¦¦¦¦¦¦#^^^^^^^^#¦¦¦¦¦¦##¦¦¦¦¦¦¦¦¦¦¦¦^^^
¦¦¦#^^^^¦¦¦¦¦¦¦¦¦¦#^^^^^^^^#¦¦¦¦¦¦#^^¦¦¦¦¦¦¦¦¦¦¦¦¦^
¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦^^^##¦¦¦¦¦¦¦^^^¦¦¦¦¦¦###¦¦¦^^
¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦####¦¦¦¦¦^^¦¦¦¦#^
¦¦¦¦¦¦¦¦¦#########^^¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦#^^^^^¦¦¦¦^
¦¦¦¦¦¦¦¦¦#^^^^^^^^^^^^^^^#¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦####¦¦¦¦#^
¦¦¦¦¦¦¦#^^^^^^^^^^^^^^^^^#¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦#^
¦¦¦¦¦¦¦¦¦¦¦¦¦¦^^^^^^#¦¦¦¦¦¦¦¦¦¦¦¦###¦¦##¦¦¦¦¦¦¦¦¦#^
¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦##¦¦¦¦¦##¦¦¦¦¦¦¦^^^
¦¦¦¦¦####¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦^^^^^^
^^^^^^^^^^^^^^^^^^^^^^^¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦¦#^^^^^^^^^^

Benefit using this design:
- follows DRY & Liskov Substitution Principle when using Ammunition interface instead of using the respective crossbow
  bolt classes / making Mantis class an extension of Actor abstract class

Alternative designs:
- Instead of having the crossbow shoot arrows from inventory everytime, make the crossbow just inflict random status
  effects on the target. This design would remove the need to create classes for bolts, but it wouldn't allow for
  bolts to be picked up and reused

When a new thing is added:
- New bolt for crossbow: Create new bolt class and make it implement Ammunition interface