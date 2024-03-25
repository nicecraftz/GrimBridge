## Installation
- Install the plugin from the [release tab](https://github.com/nicecraftz/GrimBridge/releases)
- Drag and drop the file into your server (both proxy and server)
- Reboot your server
- Enjoy


## Information
For the plugin to send punishments commands over proxy the syntax of the commands need to be changed a bit.

```yaml
Punishments:
  Combat:
    remove-violations-after: 300
    checks:
      - "Killaura"
      - "Aim"
    commands:
        # For it to be ran it requires to have 'proxy:' at the start.
      - "10:0 proxy:ban %player% cheating" 
```


## Issues
If you find any problems inside the plugin you can join my [discord server](https://discord.gg/ZYvdE9hYz8) and report it! Or if you want you can always fix it yourself and send me a cool pull request :)
