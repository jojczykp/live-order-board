# Silver Bars Marketplace

Pawel Jojczyk

* [https://github.com/jojczykp](https://github.com/jojczykp)
* [https://www.linkedin.com/in/pawe%C5%82-jojczyk-b108331/](https://www.linkedin.com/in/pawe%C5%82-jojczyk-b108331/)

## Comments

- Requirement about different Summary ordering for BUYs and SELLs suggests that we have two independent status sections,
  with no price merging between them.

- There is no requirement to present collection of original Orders, so for better performance I keep actual Summary
  state only, updating it on every incoming Order. This also limits memory consumption.
  
- To avoid Summary state modifications by code other than LiveOrderBoard, copy of it (transforming to list) is made in
  getter.

- Summary state is kept sorted. That makes registering of Order a little bit slower: (O(log(N)) instead of O(N)), but
  generating a copy to be returned by getter faster (O(N) instead O(Nlog(N))).
  
- TDD/BDD (given/when/then) for testing.

- Self-commenting code (mostly).

- KISS, YAGNI (no overengineering; i.e. no factories, interfaces introduces since problem is very simple :) )

- Standard Maven project structure.
