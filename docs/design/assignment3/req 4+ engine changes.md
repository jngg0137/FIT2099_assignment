Req 4+:
- Extend Display class to also be able to display coloured text to make some messages such as KazaaakplethKilik (Ÿ)
  pulling out their shield / taunting more obvious
- Extend MoveActorAction to check if the actor has the "Dazed" status effect before allowing them to move. If it does,
  prevent them from moving and return a different result string from the one stated in the menu. This status effect is
  applied when KazaaakplethKilik (Ÿ) collides with the player while charging at them
- Defeating KazaaakplethKilik (Ÿ) ends the game. World class's endGameMessage() is modified to accept a parameter
  that determines what text to show. Defeating KazaaakplethKilik (Ÿ) shows 'Game Won ඞ'. Dying still shows
  'Game Over'
- Extend Actor's modifyAttribute() method, extend BaseActorAttribute to have buffer() and tick() method, extend Actor to
  have a tick() method & extend GameMap's tick() method to include Actor's new tick() method to allow for logic behind
  buffer. This requirement will do buffering for only HP. Allowing shield dashes to grant(or buffer) a temporary HP barrier on top of
  current HP. So if the actor has 10 temporary HP and receives 5 damage, it absorbs the damage the actor would've taken and
  temporary HP is depleted by 5. But if the actor receives 20 damage, they will take 10 damage to actual health and deplete their temporary HP fully.
  All temporary attributes expires after one turn. If this is to be extended to other BaseActorAttributes such as mana, same logic
  applies. 

Justifications:
- Extensions made to display class allow for color coding other messages. For example, when the Tarnished is affected by
a negative status effect, the text shown for it could be red color. Positive events such as being healed can be green
color.
- Extending World class' endGameMessage() to show other types of text allows for custom game over texts in future.
- Doing everything in the fourth bullet point of req 4+ allows for other BaseActorAttributes to also be buffered. For 
example, if this were to be extended to another BaseActorAttribute such as mana and strength, same logic applies.