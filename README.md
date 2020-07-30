# CustomDeathMessages
CustomDeathMessages is a lightweight, but highly customizable plugin. It comes with default messages, which you can modify, delete, or add any of your own messages. All you have to do is install the plugin, and everything is all set to go! (Works on all versions 1.8 to 1.16)
### Link: https://www.spigotmc.org/resources/customdeathmessages-cdm.69605/
### Author: Elementeral
### Discord: Smoke#8238

## Features
* Toggleable and configurable messages sent to the victim and the killer for PvP deaths
* Toggleable and configurable global death messages.
* All death messages (natural causes, mobs, and players) all have their own spot in config which is configurable with multiple messages.
* Update checker which sends a message when the plugin has an update
* Toggleable and configurable level of speed for a configurable amount of time when you kill a player.
* Toggleable feature where the player's health is set back to the max when they kill another player.
* Toggleable lightning effect when a player is killed.
* Customizable percent chance for a player to drop their head on death. (Customizable head name)
* Extensive and very customizable config, see the example below.
* Built-in placeholders, feel free to request any to be added.
* In-Game command to change any messages with ease.
* Hex colors for all messages, to use, use &#hex

## Commands and Permissions

CDM Command:
* /cdm reload - allows you to reload config after making a change.
  * permission: cdm.reload

* /cdm add [path] [new message] - allows you to add messages to config from a command, the 'path' tab autocompletes, so no need to go looking in config.
  * permission: cdm.add.message

* /cdm list [path] - lists the messages for a death message cause, and numbers them which allows you to delete certain messages.
  * permission: cdm.list

* /cdm remove [path] [number] - allows you to delete messages from config, the path is tab completed and the number is the number of the message you want to delete, obtainable from /cdm list.
  * permission: cdm.remove.message
  * IMPORTANT: Use of the command will delete all comments in the config, due to spigot's API. If you need comments, it is suggested you edit messages manually.

* Update Checker:
  * permission: cdm.updates
  * allows players with this permission to receive a message when a plugin update is available.

## config.yml:

```yaml
# Enable Update Messages: Toggles update messages on or off.
enable-update-messages: true

# To use hex colors: &#hex
# PvP Messages only for when  a player is killed by another player, enabled by default
# Placeholders for pvp messages: %victim%, %victim-nick%, %killer%, %killer-nick%, %killer-x%, %killer-y%, %killer-z%, %victim-x%, %victim-y%, %victim-z%
enable-pvp-messages: true
killer-message: '&e[&cDeathMessages&e] &eYou killed &c%victim%'
victim-message: '&e[&cDeathMessages&e] &eYou were killed by &c%killer%'

# Toggleable features: set true to enable, false to disable. All features except lightning-effect are false by default.
# Drop Head Percentage: self explanatory, chance of a player head to drop, [0 = 0%, 1 = 100%] 0.5 by default.
# Do Lightning: creates a lightning effect that will not harm or damage anything when the victim is killed. True by default.
# Head Name: Sets the name of a head whenever dropped from a player.
head-name: "&c%victim%'s Head"
do-lightning: true

# Number between 0 and 1
drop-head-percentage: 0.5

# Enable Global Messages: self explanatory, enables global death messages.
enable-global-messages: true

# You can add messages or remove any of these messages. Toggleable by boolean above.
# This chooses a death message at random. You may add %kill-weapon% to any line in killed-death-messages.
# Placeholders for melee messages: %victim%, %victim-nick%, %killer%, %killer-nick%, %kill-weapon%, %killer-x%, %killer-y%, %killer-z%, %victim-x%, %victim-y%, %victim-z%
global-pvp-death-messages: 
    - "&c%victim% &ehas been killed by &c%killer%"
    - "&c%victim% &ewas slain by &c%killer% &eusing &7[&b%kill-weapon%&7]"
    - "&c%victim% &ehas been put down by &c%killer%"
    - "&c%victim% &ewas slaughtered by &c%killer% &eusing &7[&b%kill-weapon%&7]"
    - "&c%killer% &epulverized &c%victim% &eusing &7[&b%kill-weapon%&7]"
    - "&c%victim% &ewas destroyed by &c%killer%&e"
    - "&c%killer% &egave &c%victim% &ethe L"
    - "&c%victim% &ewas EZ clapped by &c%killer% &eusing &7[&b%kill-weapon%&7]"

# Placeholders for melee messages: %victim%, %victim-nick%, %killer%, %killer-nick%, %killer-x%, %killer-y%, %killer-z%, %victim-x%, %victim-y%, %victim-z%
melee-death-messages:
    - "&c%victim% &ewas slapped by &c%killer%"
    - "&c%victim% &ewas KO'd &c%killer%"
    - "&c%victim% &egot rocked by &c%killer%"
      
# Placeholders for ALL below messages: %victim%, %victim-nick%, %victim-x%, %victim-y%, %victim-z%
fall-damage-messages:
    - "&c%victim% &emade himself a pancake on the ground"
    - "&c%victim% &efell from a high spot"  
      
drowning-messages:
    - "&c%victim% &efound out the hard way he doesn't have gills"
    - "&c%victim% &ethought he could make it to oxygen in time"   

suffocation-messages:
    - "&c%victim% &ecouldn't catch his breath while in a block"
    - "&c%victim% &ecouldn't breathe while inside of a block"  
    
lava-messages:
    - "&c%victim% &eburned up in a pit of lava"
    - "&c%victim% &efell into a lava pool... say goodbye to his items"
    
magma-block-messages:
    - "&c%victim% &ewas removed by a hot &6&lmagma block... &ejust crouch?"
    - "&c%victim%'s &efeet were made into bacon by a &6magma block"    
    
elytra-messages:
    - "&c%victim% &eflew into a wall and died...?"
    - "&c%victim% &egot slammed into a wall by his elytra"

wither-messages:
    - "&c%victim% &egot the wither disease"
    - "&c%victim% &ewithered away to the 4th dimension"
    
suicide-messages:
    - "&c%victim% &ekilled himself"
    - "&c%victim% &ehad a mental breakdown and died"
    
fire-messages:
    - "&c%victim% &edecided to stand in the fireplace"
    - "&c%victim% &eplayed with fire"
    
fire-tick-messages:
    - "&c%victim% &ecaught on fire and did not stop, drop, and roll"
    
starvation-messages:
    - "&c%victim% &eforgot to eat"
    - "&c%victim%'s &emom said we have food at home"
    
cactus-messages:
    - "&c%victim% &ewas poked to death"
    - "&c%victim% &edied to a &a&lcactus"
    
potion-messages:
    - "&c%victim% &edied of black magic"
    - "&c%victim% &edied of coronavirus"
    
void-messages:
    - "&c%victim% &ethought it would be fun to take a trip in the void"
    
lightning-messages:
    - "&c%victim% &ewas smitten by the gods"
    - "&c%victim% &ewas struck down by the gods"
    
falling-block-messages:
    - "&c%victim% &ewas crushed by a falling block"
    
slime-messages:
    - "&c%victim% &ewas crushed by a &a&lGIANT SLIME"
    - "&c%victim% &ewas slimed to death while collecting slime balls"
    
zombie-messages:
    - "&c%victim% &ewas eaten alive by a &a&lZombie"
    - "&c%victim% &ethought a &a&lZombie &ewas friendly"

pigman-messages:
    - "&c%victim% &eaggroed the &d&lPigmen, &eso they ate him"
    
skeleton-messages:
    - "&c%victim% &egot sniped by a bony &f&lSkeleton"
    - "&c%victim% &egot shot by a &f&lskeleton"

magmacube-messages:
    - "&c%victim% &egot crushed by a &c&lGIANT MAGMA CUBE"
    
husk-messages:
    - "&c%victim% &egot eaten by a dessert dweller AKA a Husk"
    
spider-messages:
    - "&c%victim% &etried his luck against his worst fear... &c&lA SPIDER!"
    
cavespider-messages:
    - "&c%victim% &egot bitten to death by a &c&lCave Spider"
    
witherskeleton-messages:
    - "&c%victim% &egot destroyed by a &f&lWither Skeleton &ewith a stone sword"
    
witherboss-messages:
    - "&c%victim% &egot his head blown off by the Wither"
    
    
dragon-messages:
    - "&c%victim% &egot his guts ripped out by the &d&lEnder Dragon"
    
elderguardian-messages:
    - "&c%victim% &etried to clear a ocean monument but the &7&lElder Guardian &esaid no"
    
tnt-messages:
    - "&c%victim% &egot his head blown off by &c&lTNT"
    
creeper-messages:
    - "&c%victim% &egot blown up by a &a&lCreeper Ahhhh Man"
    
ghast-messages:
    - "&c%victim% &egot fireballed by a &f&lGhast"
    
enderman-messages:
    - "&c%victim% &elooked an &d&lEnderman &ein the eyes"
    
silverfish-messages:
    - "&c%victim% &egot eaten by a &7&lSilverfish"
    
witch-messages:
    - "&c%victim% &edied to a &d&lWitch named Scarlett"
    
shulker-messages:
    - "&c%victim% &edied trying to get some &d&lshulker &eshells"
    
guardian-messages:
    - "&c<cictim> &egot killed by a &7&lGuardian... &esomehow?"
    
golem-messages:
    - "&c%victim% &emade an &7&lIron Golem &emad"
    
zombievillager-messages:
    - "&c%victim% &ewas made dinner of by a &a&lZombie Villager"
    
endermite-messages: 
    - "&c%victim% &egot eaten by the &d&lEndermans &ehater, an &d&lEndermite"
    
phantom-messages:
    - "&c%victim% &ewas kidnapped by a &3&lPhantom"
    
drowned-messages:
    - "&c%victim% &ewent for a late night swim and got eaten by a &b&lDrowned Zombie"
    
pillager-messages:
    - "&c%victim% &egot shot by a &7&lPillager"

ravager-messages:
    - "&c%victim% &egot rammed by a &7&lRavager"

illusioner-messages:
    - "&c%victim% &ewas sent to the illusion world by an Illusioner"
    
bee-messages:
    - "&c%victim% &ewas stung by a &lBEE"
    
wolf-messages:
    - "&c%victim% &ewas ripped apart by a &f&lwolf"
    - "&c%victim% &eprovoked a &f&lwolf"
    
llama-messages:
    - "&c%victim% &ewas spit to death by a Llama"
    - "&c%victim% &ecould not handle being spat on by a Llama"
    
blaze-messages:
    - "&c%victim% &ewas charred by a &6&lblaze"
    - "&c%victim% &ewas turned into charcoal by a &6&lblaze"
    
stray-messages:
    - "&c%victim% &ewas domed by a stray"
    
vex-messages:
    - "&c%victim% &ewas eaten by a tiny flying (and annoying) &f&lvex"
    
vindicator-messages:
    - "&c%victim% &ewas hunted down by a &f&lvindicator"
    - "&c%victim% &efound Johnny... a &f&lvindicator?"
    
pufferfish-messages:
    - "&c%victim% &ewas pricked by an expanding sea cactus... please re-evalute your life"
    
polar-bear-messages:
    - "&c%victim% &ewas murdered by a nice &f&lpolar bear... &ewhich means he hit it! (someone kill him)"
    - "&c%victim% &egot turned to snow by a &f&lpolar bear"
    
dolphin-messages:
    - "&c%victim% &eprovoked a nice dolphin... what did they do to you?"
    - "&c%victim% &ehit a dolphin and they retaliated"
    
panda-messages:
    - "&c%victim% &ewas made into a pandas dinner"
    - "&c%victim% &etried stealing a pandas bamboo"
    
piglin-messages:
    - "&c%victim% &emet his maker against the new piglins"
    - "&c%victim% &emade the piglins mad. I mean, we warned you, don't steal from them..."
    - "&c%victim% &emissed the old zombie pigmen so he took his anger out on a piglin... and lost"

hoglin-messages:
    - "&c%victim% &ewas charged by a hoglin, kind of rude?"
    - "&c%victim% &edidn't read patch notes about hoglins"
    
zoglin-messages:
    - "&c%victim% &eate a zoglins horn for dinner"
    - "&c%victim% &ewas rammed by a zoglin"
    
zombified-piglin-messages:
    - "&c%victim% &etook a piglin to the overworld and was murdered by their new, not so friendly pet"
    - "&c%victim% &edied to a transformed piglin"

# This is for any messages that haven't been added, so if you die to a cause which doesn't have any messages,
#  one of these messages will display instead. (Please report any missing messages in my spigot discussion)
unknown-messages:
    - "&c%victim% &edied to unknown causes"
    - "&c%victim% &edied"
    - "&c%victim% &ewas killed"
    
# The following messages only apply to projectiles shot by a mob, and not a player
fireball-messages:
    - "&c%victim% &ewas fireballed until he burnt to death"
    
arrow-messages:
    - "&c%victim% &elooked like a porcupine after getting shot to death"

# Only here to help me debug my plugin, not suggested you enable.
developer-mode: false

```
