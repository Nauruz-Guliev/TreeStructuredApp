# :star: Tree Structured App

An app that can replicate the structure of a tree. It's possible to go endlessly in depth and create as many nodes as possible at any level (depth). 
Additionally, node deletion is also implemented with the left to right swipe gesture. The names for the nodes are generated automatically using 
keccak-256 algorithm. 

## :hammer_and_wrench: Tech stack
| Technology | What it iss used for: |
| --- | --- |
| `Room` |  node storage |
| `Dagger 2` | dependency injection |
| `MapStruct` | generating boilerplate mapper classes |
| `Navigation component and SafeArgs` | safely navigating with arguments to the same fragment |
| [Keccak](https://github.com/komputing/KHash) | hash generation for node names |
