

<!-- PROJECT LOGO -->

<br />
<div align="center">
  <a href="https://github.com/DanielSant0s/GTAVLStuff">
    <img src="https://user-images.githubusercontent.com/47725160/137574001-369143ae-d1ae-4507-84d1-fcd683c53931.png" alt="Logo" width="250" height="220">
  </a>

  <h3 align="center">GTA V: Legacy - Base code and some script stuff</h3>

  <p align="center">
    Here is some vital code for the functioning of the GTA V Legacy project, created for PlayStation 2, from purely visual changes, to fixes for problems of the original game (GTA San Andreas v1.03), includes so many patches made in machine code , as in GTA3Script, and a lot of raw memory manipulation, enjoy :P
  </p>
</div>

## GTA3Script

![vscode-gta3sc](https://user-images.githubusercontent.com/47725160/137574423-f05b118a-1117-4234-8cfd-a2e5e7b36aa9.gif)

Here is some code created using the scripting language GTA3Script, essentially some gameplay mechanics and superficial features like smartphone, menus and everything else.

<p align="right">(<a href="#top">back to top</a>)</p>


### Phone

![bandicam_2021-05-29_12-52-34-407](https://user-images.githubusercontent.com/47725160/137574662-500b485f-a898-4859-95dc-22a74147227c.gif)


This code is currently found almost purely visual. Seeks to replicate the look (and future operation) of GTA V smartphones. PS: Very poorly optimized, needs an intensive polishing.

[GTA V Legacy phone script](https://github.com/DanielSant0s/GTAVLStuff/blob/main/gta3sc/vphone.sc)

### Trainer

https://user-images.githubusercontent.com/47725160/137574836-e0f61db9-205b-48a6-90a0-a58ae0910c5e.mp4

In addition to a trainer, it is also a sample script for a menu style based on GTA V. It is complete and has all the necessary functions to build any menu, with only minor edits to the original code to meet the needs.

[GTA V Legacy Trainer script](https://https://github.com/DanielSant0s/GTAVLStuff/blob/main/gta3sc/vmenu.sc)

## Hardcoded Patches

![IDA Screen](https://user-images.githubusercontent.com/47725160/137575013-1e568b95-ea7e-4ede-8245-00a81423937c.png)


In this section the majority of GTA V Legacy patches are explained, raw patches in .pnach(PCSX2) format, created with months of reverse engineering and calculated redeployment in Assembly, most patches are designed to work without violating the logic and size of the original code.

[GTA V Legacy base code patch](https://github.com/DanielSant0s/GTAVLStuff/blob/main/patch/AAE75441.pnach)

- Vanilla Fixes: The first section of the patch has fixes designed to fix problems and defects in the original game, created based on SilentPatch.
- HUD Patches: Here is code working over the top of the HUD to make it "widescreen" and also tweak fonts and measurements to look like GTA V.
![hud-screen](https://user-images.githubusercontent.com/47725160/137575280-4bb37ba9-bf36-425e-b1c6-07504a99dbcf.png)
- Controls: This section is responsible for changing the gameplay control buttons as explained in the comments.
- Menu: Okay, this part is a little tricky. Basically this section is a big patch that visually and functionally restructures the original PS2 GTA San Andreas menu, with the intention of modernizing it.

https://user-images.githubusercontent.com/47725160/137575369-1137372b-4d63-40b2-9da0-c662ba1c1577.mp4

- Folder structure: This part is related to changing the structure of folders and files in the game, no biggie.
- Experimental: Experimental.



