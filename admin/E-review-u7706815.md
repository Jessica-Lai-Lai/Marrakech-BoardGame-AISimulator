## Code Review

Reviewed by: Aaron Nathan Vas, u7706815

Reviewing code written by: Jessica Lai, u7741198

Component: <the component being reviewed>


public static String moveAssam(String currentAssam, int dieResult){
// FIXME: Task 13

        int x= Integer.parseInt( currentAssam.substring(1, 2));
        int y=  Integer.parseInt(currentAssam.substring(2, 3));

        int[] currentPosition = new int[]{x,y}; // 初始坐标
        String currentDirection = currentAssam.substring(3, 4);

        for (int i = 0; i < dieResult; i++) {
            // 尝试向当前方向移动一步
            int[] newPosition = move(currentPosition, currentDirection);


            // 检查新位置是否在合法范围内（7*7的棋盘）
            if (isValidPosition(newPosition)) {
                currentPosition = newPosition;

            } else {
                // 如果新位置不合法，需要转向與更新座標
                currentDirection = turn(currentDirection);
                currentPosition = newMove(currentPosition, currentDirection);
            }
        }
        // 打印最终结果

        String newAssam = "A" + currentPosition[0]+ currentPosition[1] + currentDirection;
        return newAssam;
    }

    // 向指定方向移动一步
    public static int[] move(int[] position, String direction) {
        int x = position[0];
        int y = position[1];

        switch (direction) {
            case "N":
                return new int[]{x , y-1};
            case "S":
                return new int[]{x , y+1};
            case "E":
                return new int[]{x+1, y };
            case "W":
                return new int[]{x-1, y };
            default:
                return position;
        }
    }


    // 判断坐标是否在合法范围内
    public static boolean isValidPosition(int[] position) {
        int x = position[0];
        int y = position[1];
        return x >= 0 && x <= 6 && y >= 0 && y <= 6;
    }


    // 根据当前方向返回新方向（转向）
    public static String turn(String currentDirection) {
        switch (currentDirection) {
            case "N":
                return "S";
            case "S":
                return "N";
            case "E":
                return "W";
            case "W":
                return "E";
            default:
                return currentDirection;
        }
    }

### Comments 


Favorite Code:
Handling Invalid Positions: The code handles invalid positions on the chessboard appropriately by changing direction and calculating a new position. This logic is well-structured in the moveAssam method.
Switch Statements: The use of switch statements in the move, turn, and newMove methods makes the code more concise and readable, especially when dealing with directional changes.

Innovative Code:
Directional Changes: The code efficiently handles directional changes using switch statements and even correctly handles changes that move Assam along the edges of the chessboard.

Room for improvement:
Comments and Documentation: While the existing comments are helpful, it would be beneficial to add more comments to describe the overall purpose of the moveAssam method and any specific logic or edge cases that might not be immediately obvious.
Java Naming Conventions: In Java, variable and method names are typically in camelCase, so currentAssam could be renamed to currentPosition for consistency.

