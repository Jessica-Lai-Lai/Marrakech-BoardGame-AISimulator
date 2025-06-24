
# Test plan

## List of classes
 `Assam`

| methodName                                             | testCodeAssignedTo | isTestIsolation | exampleIfNotIsolation |
|--------------------------------------------------------|--------------------|-----------------|-----------------------|
| public Assam(String gameString)                        | u7757596           | Yes             |                       |
| public static int readDirection(char c)                | u7757596           | Yes             |                       |
| static Integer getAssamIndex(String gameString)        | u7757596           | Yes             |                       |
| public static String getAssamString(String gameString) | u7757596           | Yes             |                       |

 `Board`

| methodName                                                    | testCodeAssignedTo | isTestIsolation | exampleIfNotIsolation                                                                                                                                                                                                                                                                    |
|---------------------------------------------------------------|--------------------|-----------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Board(String gameString)                                      | u7757596           | No              | In this method Tile.constructBoard(gameString), Player.constructPlayerList(gameString) and constructor Assam(gameString) are called to construct the Board, and the above three methods are tested isolate, so if they passed their test code, this method will automatically be correct |
| public static int charToInt(char c)                           | u7757596           | Yes             |                                                                                                                                                                                                                                                                                          |
| public static String getBoardString(String gameString)        | u7757596           | Yes             |                                                                                                                                                                                                                                                                                          |
| public static Carpet[] findAllFullCarpet(Tile[][] boardTiles) | u7757596           | Yes             |                                                                                                                                                                                                                                                                                          |


`Carpet`

| methodName                                           | testCodeAssignedTo | isTestIsolation | exampleIfNotIsolation |
|------------------------------------------------------|--------------------|-----------------|-----------------------|
| public static Carpet readCarpet(String carpetString) | u7706815           | yes             |                       |
| public boolean isPositionValid()                     | u7706815           | yes             |                       |
| public boolean isSegmentsAdjacent()                  | u7706815           | yes             |                       |
| public boolean isPlayerValid()                       | u7706815           | yes             |                       |
| public boolean isOnBoard(Tile[][] currentBoard)      | u7706815           | yes             |                       |
| public void setRotation()                            | u7706815           | yes             |                       |

`Coordinates`

| methodName                                    | testCodeAssignedTo | isTestIsolation | exampleIfNotIsolation |
|-----------------------------------------------|--------------------|-----------------|-----------------------|
| public boolean equalsTo(Coordinates position) | u7706815           | yes             |                       |

`Player`

| methodName                                                            | testCodeAssignedTo | isTestIsolation | exampleIfNotIsolation |
|-----------------------------------------------------------------------|--------------------|-----------------|-----------------------|
| public static Player getPlayerInList(Player[] currentPlayers, int id) | u7741198           | yes             |                       |
| public static Player[] constructPlayerList(String gameString)         | u7741198           | yes             |                       |
| public static Player constructPlayer(String singlePlayerString)       | u7741198           | yes             |                       |
| public static String getPlayerString(String gameString)               | u7741198           | yes             |                       |
| public static int idToInt(char c)                                     | u7741198           | yes             |                       |
| public boolean isMoneyValueValid(int money)                           | u7741198           | yes             |                       |

`Tile`

| methodName                                                                                               | testCodeAssignedTo | isTestIsolation | exampleIfNotIsolation |
|----------------------------------------------------------------------------------------------------------|--------------------|-----------------|-----------------------|
| public static Tile constructTile(Coordinates tilePosition, Coordinates AssamPosition, String tileString) | u7741198           | yes             |                       |
| public static Tile[][] constructBoard(String gameString)                                                 | u7741198           | yes             |                       |


* List below all classes in your implementation that should have unit tests.
* For each class, list methods that can be tested in isolation.
* For each class, if there are conditions on the class' behaviour that cannot
  be tested by calling one method in isolation, give at least one example of
  a test for such a condition.

Do **not** include in your test plan the `Marrakech` class or the predefined
static methods for which we have already provided unit tests.
