# SeLoger
## Contexte
Création d'une application dans le cadre de tests techniques 

## Choix techniques
* Clean architecture(3 modules : presentation, domain, data) => meilleur découpage, plus facilement testable et scalable. Indépendance de chaque module
* presentation connnaît domain et data
* data connaît domain
* domain ne connaît personne

* module data : le point d'entrée du module est un repository qui va être en charge de lancer les appels réseaux (Retrofit)

* module domain : c'est un module sans contexte android, il contient les objets métiers, les usecases et l'interface du repository

* module presentation : J'ai choisi pour ce module de faire du MVI (Model View Intent), ça fait quelques temps que je souhaitais tester ce pattern, j'ai profité de ce test pour le faire. On entends par Intent une action (ce n'est pas l'intent d'android), souvent un input de l'utilisateur.La vue transmet cet Intent (dans le projet MainScreenAction par exemple) au viewModel qui lui renvoi un état immuable (MainScreenState) en retour. Cela représente un principe fonctionnel l'unidirectionnel Data Flow. C'est une structure assez fermée qui a de nombreux avantages : 
*  l’UI reflète à chaque instant l’état du ViewModel. 
* Les états sont prédictibles et facilement testables.
* Un développeur nouveau venu, une fois qu'il a compris le principe, peut aisément intervenir dans toute l'appli
* Tous les états de la vue et des différentes actions sont centralisés dans un fichier (MainContract) permettant de savoir ce que fait cet écran dans sa globalité.

J'ai fait le choix aussi d'utiliser une single activity et le navigation component, j'apprécie ce principe et sa facilité de mise en place.

## Temps passé
* Vendredi> 4h => Mise en place archi, module data et domain
* Samedi> 5h => module presentation
* Dimanche> 2h => relecture, suppression code mort, optimisation
* TOTAL => environ 11h

## librairies utilisées
* appels réseaux => Retrofit
* observation Ui => livedata
* affichage des images => Coil
* affichage des loading => Shimmer layout
* navigation => navigation component
* injection de dépendances => Koin



