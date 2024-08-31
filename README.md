# README

## Features

- Support for LuckPerms
- Support for economy mods using OctoEconomyAPI (ex. EightsEconomyP)
- Players with sufficient permissions can use commands to create gym badges

## Configuration

### Overview

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

### Case 1 Using `Vanilla` Economy

```json
{
  "economy": "Vanilla",
  "vanillaCurrencyItem": "minecraft:iron_ingot",
  "gymBadgeCreatePrice": 4
}
```

Players with sufficient permission can create gym badges for the price of four iron ingots 

### Case 2 Using `OctoEconomy` Economy

```json
{
  "economy": "OctoEconomy",
  "vanillaCurrencyItem": "minecraft:diamond",
  "gymBadgeCreatePrice": 100
}
```

Players with sufficient permission can create gym badges for 100 dollars (or whatever currency their servers use)

## Commands

### With LuckPerms installed

Given that a player has sufficient permission (`fcgymbadges.firebadge.create`), the player can execute following command to create Fire Badge

```
/fcgymbadges firebadge create
```

### Without LuckPerms installed

Only players with op permission (minimum level of 2) can use the commands