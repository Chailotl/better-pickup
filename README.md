<p align="center">
  <img src="https://i.imgur.com/faK0EcR.png">
</p>

Minecraft's pickup system has many flaws that this plugin aims to fix. Many aspects of the pickup system can be configured, such as:
- Make block drops automatically pickup
- Make block drops invulnerable
- Change the pickup delay for block drops
- Change the pickup delay for player dropped items
- Pick up your own dropped items faster

<p align="center">
  <img src="https://i.imgur.com/5QWEOEe.gif">
</p>

## Usage
It will automatically work. If you want instant auto pickup, enable auto pickup and set the block drop delay to 0.

## Config
```yaml
# Whether block drops should automatically be picked up
autoPickup: true

# How long in ticks until block drops can be picked up (vanilla is 10 ticks)
blockDropDelay: 10

# Whether block drops should be invulnerable
invulnerable: true


# How long in ticks until dropped items can be picked up (vanilla is 40 ticks)
playerDropDelay: 20

# How long in ticks until other players can take items you drop
stealDelay: 40
```

## Commands
**/betterpickup info** - Plugin info

**/betterpickup reload** - Reload config (OP)

## Permissions
betterpickup.reload (OP)
