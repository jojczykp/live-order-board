# Silver Bars Marketplace

Pawel Jojczyk

* [https://github.com/jojczykp](https://github.com/jojczykp)
* [https://www.linkedin.com/in/pawe%C5%82-jojczyk-b108331/](https://www.linkedin.com/in/pawe%C5%82-jojczyk-b108331/)

## Comments

- Requirement about different _Summary_ ordering for BUYs and SELLs suggests that we have two independent status sections,
  with no price merging between them.

- There is no requirement to present collection of original _Orders_, so for better performance I keep actual _Summary_
  state only, updating it on every incoming _Order_. This also limits memory consumption.
  
- To avoid _Summary_ state modifications by code other than _LiveOrderBoard_, copy of it (transforming to list) is made in
  getter. Price for it is performance (_O(N)_ instead of _O(1)_ in getter).

- _Summary_ state is kept sorted. That makes registering of _Order_ a little bit slower: (_O(log(N))_ instead of _O(1)_), but
  generating a copy to be returned by getter faster (O(N) instead O(Nlog(N))).
  
- TDD/BDD (given/when/then) for testing.

- Self-commenting code (mostly).

- _KISS_, _YAGNI_ (no overengineering; i.e. no factories, interfaces introduces since problem is very simple :) )

- Standard Maven project structure.
