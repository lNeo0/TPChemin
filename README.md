# TPChemin
Tp sur les chemins 

Josué Vidrequin 

Bonjour,

Voici le rendu de mon TP sur les chemins.

PS : Si vous n'arrivez pas à le lire le .zip ou quoique ce soit dîtes le moi je vous enverrais mon lien privée GitHub. 

La logique était ici de regarder selon les critères insérés  dans les Textfields. Le trajet le moins coûteux en fonction de ces critères. 
- La carte s'affiche dans carte -> carte Générale 
- Le diagramme Nb Trajet -> Diagramme 
- L'insertion de deux villes valides est obligatoire 
- On exporte la carte une fois la recherche validée
- Les critères sont un minimum. On demande une valeur en dessous de celle placée. (ex: si on veux un coût de 50, sachant que 50 est le coût optimal et bien <60)
- Le table va retourner et afficher les valeurs renvoyées par l'algorithme
- On peut ajouter/ supprimer des villes et des trajets, tout est modulable et non écrit en dur

Meilleur Trajet : 
         - Lorsqu'il n'y a pas de critère, il va retourner le trajetSimple le plus optimal dans la généralité. C'est à dire bien dans les quatre critères. 
         - Lorsqu'il choisit un seul critère, il va négliger les autres et chercher à trouver le trajet le moins coûteux sur ce critère. 
         - Lors qu'il y en a deux, trois et quatre critères, l'idée reste la même, l'algorithme va chercher à retourner le meilleur trajet en négligeant les critères qui n'ont pas été remplis. 

Comme vous aurez l'occasion de voir, j'ai travaillé également sur les chemins composés. Ce qui est malheureusement encore avec quelques problèmes, ma logique dessus était de 
récupérer la ville de départ, récupérer tous les trajets qui partaient de cette ville calculer le meilleur trajet et continuer comme ça tant que l'on ne l'a pas trouvé. 
J'en suis arrivé au stockage des variables, pour ensuite se dire est-ce plus petit oui / non et retourner le trajet composé. 

L'un des problèmes que j'avais, était le temps. Cette manière de rechercher n'est clairement pas optimale, et prends beaucoup de ressource inutilement je trouve. 
Certaines classes sont donc inutilisées car elles servent au bon fonctionnement du trajet composé. 

Merci d'avance, bien à vous. 

