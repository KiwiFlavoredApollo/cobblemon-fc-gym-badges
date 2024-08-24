# README

## Features

- Support for LuckPerms
- Support for economy mods using OctoEconomyAPI (ex. EightsEconomyP)
- 

## Configuration

`config/fcgymbadges/config.json`

```json
{
  "economy": "None",
  "vanillaCurrencyItem": "minecraft:diamond",
  "gymBadgeCreatePrice": 0
}
```

- `economy`: `Vanilla` or `OctoEconomy`   
- `vanillaCurrencyItem`: Any item specified by id (ex.`minecraft:gold_ingot`)
- `gymBadgeCreatePrice`: Non-negative floating point number

Economy feature can be disabled if `economy` is set to any other random strings

## Commands

## With LuckPerms installed

Given that a player has sufficient permission (`fcgymbadges.firebadge.create`), the player can execute following command to create Fire Badge

```
/fcgymbadges firebadge create
```

## Without LuckPerms installed

Only players with op level (minimum of 2) can use the commands