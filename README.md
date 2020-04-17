# Ad Management Kata

This kata is a review of a previous Kata I did. 
You can find the oldest version [here](https://github.com/felipefcor/ad-management-kata).

Regarding the previous Kata I did some changes in the approach to resolve the Kata.

First of all, I start with [classic TDD](https://en.wikipedia.org/wiki/Test-driven_development) starting for the Ad class. 
I did also a new class to put all the logic to add, remove and list Ads, an Ad Catalog. 

I followed the same 4 sprints structure (and add some refactoring new branches after that).

As I said before, I did only domain or business logic and its structure. I haven't done services or repositories.

Another significant issue it was I decided to get rid of [DTOS](https://en.wikipedia.org/wiki/Data_transfer_object).
Instead of DTOS, to break encapsulation I used getters because I didn't implement services or repositories and all was domain.
I have been tried to be prudent in not using getters and where I  have thought convenient I used "Tell don't Ask" strategy.

Also to consider I used two different [Design Patterns](https://refactoring.guru/design-patterns):
 - [Strategy Pattern](https://refactoring.guru/design-patterns/strategy) to set up the diferent expiration or remove of the ads in the purge method.
 - [Observer Pattern](https://refactoring.guru/design-patterns/observer) to notify the Users when an Ad has been removed from the Catalog