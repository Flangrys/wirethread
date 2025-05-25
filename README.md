# Wirethread | Fast and simple-to-use Minecraft Server.

Wirethread is an open source project that aims to facilitate the development and implementation of Minecraft servers
through the use of a complete and easy-to-use API.

Our goal is to provide a fast and simple way to build Minecraft servers using logical units already existing in the API,
allowing you to extend them or create new gameplay experiences without many concerns.

DISCLAIMER: This project is NOT a replacement for Spigot, Paper, or any other server; therefore, none of the mentioned
servers are compatible with this project or its API.

# Table of Contents

- [Wirethread | Fast and simple-to-use Minecraft Server.](#wirethread--fast-and-simple-to-use-minecraft-server)
    - [Table of Contents](#table-of-contents)
    - [Installation](#installation)
        - [Requirements](#requirements)
    - [Usage](#usage)
        - [Docker](#use-with-docker)
        - [Dependency](#use-as-a-dependency)
        - [Program](#use-as-a-program)
    - [Why Wirethread?](#why-wirethread)
        - [Features](#features)
        - [API](#api)
    - [Advantages vs Disadvantages](#advantages-vs-disadvantages)
    - [Contributing](#contributing)
    - [Credits](#credits)
    - [License](#license)
    - [References](#references)

# Installation

DISCLAIMER: This project is a work in progress and is not yet intended for use in production.

## Requirements

# Usage

## Use with Docker

## Use as a dependency.

## Use as a program.

# Why Wirethread?

Wirethread aims to be a tool for the development of cross-platform servers, supporting all game versions, with a modular
and extensible architectural approach, a complete API, and ease of use. Additionally, Wirethread natively adds support
for certain features that are only possible through third-party plugins,
such as backward compatibility between game versions, support for both editions of the game, and simultaneous
multi-world support.

Unlike other projects, Wirethread breaks with current development paradigms to provide a more straightforward way to
create servers, equipping developers with modern and powerful tools that allow them to program more compact and
efficient logic units.

## Features

### Adaptadores de red

### Inyector de dependencias

### Inyeccion de Plugins

### Inyeccion de Versiones

### Gestion de bloques, chuncks y dimesiones.

## API

### WARNINGS:

- The `com.wirethread.network.buffer` module is subject to removal in version `0.1.0` as it is no longer necessary for
  development.
- The `com.wirethread.server` package is subject to breaking changes regarding the management of workers and their
  lifecycle.

Many of the API features are not yet fully implemented or documented, so we do NOT guarantee their correct operation
throughout the server's lifecycle. Therefore, if your goal is to use the UNSTABLE Wirethread API, we recommend doing so
with extreme caution and at your own risk.

## Advantages vs Disadvantages

# Contributing

# Credits

# License

Wirethread is licensed under the GPL-3.0 License. See the [LICENSE](LICENSE) file for more information.

# References

[wiki.vg ](https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Main_Page)

[PriesmarineJS | Protocol ](https://prismarinejs.github.io/minecraft-data/protocol/)
