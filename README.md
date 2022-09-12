# Symbol-Table-with-Binary-Search-Tree
In this project a symbol table is implemented in the form of a binary search tree and used to record suspects. Firstly there are 2 basic classes `Suspect` and `TreeNode` which they refer to the suspects and the nodes of the tree respectivly. For each suspect an import is being made to the tree with key being his AFM. Specifically the nodes in the tree are of type TreeNode and each contains an object of type Suspect. Additionally the nodes have pointers for the left and right subtree and a field stating how many nodes there are in the subtree starting from the specific node. Lastly there is a list implementation in `List` and `ListNode` used by the tree.

The symbol table class `ST` is implemented using the above classes and has some basic functions such as:
* `searchByLastname`
* `insert`
* `insert_at_root`

In the main app the user can load suspects in the tree from a txt file with the specific format AFM first_name last_name savings taxed_income or insert a suspect himself. All this is operated in a cmd menu.
# HowTo
Open cmd in the same folder as the .java files and run the commands below:

`javac mainApp.java`

`java mainApp`
