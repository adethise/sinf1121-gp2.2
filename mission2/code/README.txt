Explications sur le fonctionnement du programme :

La classe principale est OperationTree. Il s'agit d'un objet contenant une
seule variable, le premier noeud de l'équation (type : OperationNode).
Cette classe dynamique contient les mêmes méthodes que OperationNode.

En statique, elle contient aussi la méthode (publique) parseEquation. Cette
méthode n'est pas super élégante, c'est du string matching brutal. Elle aura
certainement besoin de vérification, mais il faut que tous les nodes soient
créés pour pouvoir la tester.
Il y a aussi 2 méthodes protected : une surcharge de parseEquation et
findMatchingParenthesis. Elles ne sont utiles que pour parseEquation.

Enfin, pour les nodes, j'ai gardé une version simple, unidirectionnelle. Si
vous voulez pouvoir simplifier les dérivées il faudra rajouter un lien vers le
parent, mais après avoir testé c'est compliqué (créer un nouveau noeud qui
pointe vers son parent et vers lequel le parent pointe).
Donc j'ai estimé que d'avoir une belle version toString de la dérivée n'était
pas très important, considérant la surcharge de travail impliquée. L'important
est de pouvoir l'estimer.

Pour les nodes, regardez l'interface et les exemples. C'est simple comme
bonjour. ConstNode et VarNode sont déjà faites.


TODO : derniers nodes, debug, input
