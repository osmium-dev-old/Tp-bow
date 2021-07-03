#Teleport Bow

---
## How to get a teleport bow
1. Use command /tpbow or /tpbow [playerName] to get or give a Teleport Bow
2. Shoot an arrow and you will get teleported where the arrow land

Command aliases: /getTpBow, /getTpBow [playerName]

## How to use the config.yml file
The config.yml file allows you to customize messages, the lore of the bow and much more.

### Particle configuration
In the config.yml file insert a `particle:` parameter followed by the particle name of your choice, 
or do nothing to use the default particle. Default value is END_ROD.

### Max arrow tick lived configuration
In the config.yml file insert a `max-arrow-tick-live`: parameter followed by the amount of tick
an arrow can live. This way if the arrow stuck somewhere after sometime (default is 700 ticks) the program will forget the arrow 
and the player can use the teleport bow again. Example: The arrow may get stuck if it enter the end portal.

### The bow name configuration
In the config.yml file insert a `bow-name:` parameter followed by the name of the bow you prefer.
You can use Minecraft Color Code (&a, &b ...). The default value is `&bTeleport &fBow`

### The bow lore configuration
In the config.yml file insert a `bow-lore:` parameter followed by a string list of the 
lore you which to have. 

The default value is
```
bow-lore:
  - '&6Shoot teleporting Arrow.'
  - '&8You may still take fall damage!'
```

### The bow enchantments configuration
In the config.yml file insert a `bow-enchantments:` parameter followed by a list of string
using this syntax: `<name>:<level>`.

The default value is
```
bow-enchantments:
  - 'infinity:1'
```

### Message when receiving the bow
In the config.yml file insert a `player-get-bow-message:` parameter followed a the message
the player who receive a Teleport Bow get. The default value is `&bYou received a &bTeleport &fBow!`.

### Message when giving the bow
In the config.yml file insert a `sender-give-bow-message:` parameter followed by the message the person who 
give a Teleport Bow see. You can use `%player%` to reference the player the sender give the bow to.
The default value is `&bYou gave a &bTeleport &fBow to &c%player%!`.

## Permission
The only permission this plugin has for now is `polaki.command.tpbow` for the command `gettpbow`