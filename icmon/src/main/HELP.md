# Description du jeu ICMon

Pour lancer le jeu il suffit juste d'executer la classe Play.java qui se retrouve dans le path src/main/java/ch.epfl.cs107.icmon

**Controles**
Fléches directionnelles / ZQSD / WASD: déplacement / navigation des menus
L / F: Interaction/Collecte de Pokeball
R: Ouverture du menu de pause
Espace: Deffiler le dialogue/introduction et conclusion du combat
Entrée / E: Choix de l'attaque durant un combat

## Scenario du jeu ICMon

- Le jeu commence par une introduction (Introduction Event) qui est un mot de bienvenue avec le personnage qui spawn dans la maison. Le personnage n'a pas encore de pokemons dans sa collection
- Ensuite l'evennement de 1ere interaction avec Prof Oak commence, Le personnage doit parler a Prof Oak pour recevoir son premier Pokemon (l'assistant lui dira daller voir Prof Oak durant cet event)
- Ensuite l'evennement de Collecte de Pokeball commence, une PokeBall apparait dans l'aire Town, on pourra la collecter en Appuyant sur L/F, elle nous donnera un nouveau pokemon (Bulbizarre)
- Il ya aussi un Bulbizarre dans l'arene qu'on peut battre à tout moment dans le jeu (tant qu'on a un pokemon dans notre collection, sinon un dialogue apparaitra pour nous dire qu'on peut pas)
- Ensuite il faudra battre Garry qui se retrouve dans House

## Extensions

- Menu de pause (il suffit de cliquer sur R, ce menu nous permet de resume,restart ou quit le jeu)
- Menu de selection de Pokemon avant de commencer un combat
- Dialogues avec les autres NPCs rajoutés/ajustés durant les evennements
- Dialogue pour prevenir qu'on n'a pas de pokemon si on decide d'interagir avec un pokemon visible sans avoir de pokemon dans notre liste
- Garry a plusieurs pokemons et en choisit un au hasard
- Nouveaux pokemons aux stats variées à récuperer (Nidoqueen en récupérant la ball, Dracofeu dans le Shop et Carabaffe dans l'eau)
  our Dracofe- Nouvelle attacks en combat pour les pokemons (Heal pour quelques Pokemons, Hades Flame pour Dracofeu, Thunderbolt pour Pikachu, Solar Beam pour Bulbizarre et Special Beam pour Latios)
- Spawn aléatoire d'un Carabaffe dans les zones d'eau
- Panneau à coté de l'arena interactif
- Deux nouvelles maisons ajoutées
- Des sons lors de l'introduction du jeu, d'un combat et d'une collecte de pokeball, (ainsi qu'un autre son qui fait partie d'une easter egg)

## Images supplémentaires

Les images ajoutées via les extensions sont soit tirées du site donné dans l'énoncé <https://bulbapedia.bulbagarden.net/wiki>
ou créer personnelement à l'aide d'un logiciel de montage photo
