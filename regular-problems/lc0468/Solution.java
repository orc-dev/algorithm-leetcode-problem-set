/**
 * @author: orc-dev
 * @update: Jan.14 2024
 * 
 * @leetcode: 468. Validate IP Address
 * @tag: string
 * @see: 65. Valid Number
 */
class Solution {
    public String validIPAddress(String queryIP) {
        int dot =  0;  // IPv4 dot count
        int num = -1;  // IPv4 segment (number)
        int col =  0;  // IPv6 colon count
        int hex = -1;  // IPv6 segment (hex-char count)

        for (char c : queryIP.toCharArray()) {
            if (c == '.') {
                if (dot < 0 || dot == 3 || !isOctet(num)) {
                    return "Neither";
                }
                dot++;
                num = -1;
                col = -1;
            }
            else if (c == ':') {
                if (col < 0 || col == 7 || hex < 0) {
                    return "Neither";
                }
                col++;
                hex = -1;
                dot = -1;
            }
            else if (isHexAtoF(c)) {
                if (dot > 0 || hex == 3) {
                    return "Neither";
                }
                hex++;
                dot = -1;
            }
            else if (isDigit(c)) {
                if (dot >= 0) {
                    if (num == 0) {
                        return "Neither";
                    }
                    if (num == -1) {
                        num = 0;
                    }
                    num = (num * 10) + (c - '0');
                }
                if (col >= 0) {
                    if (hex == 3) {
                        return "Neither";
                    }
                    hex++;
                }
            }
            else
                return "Neither";
        }
        
        if (dot == 3 && isOctet(num)) {
            return "IPv4";
        }
        if (col == 7 && hex >= 0) {
            return "IPv6";
        }
        return "Neither";
    }

    /** Returns true if 'num' in range [0:255] */
    private boolean isOctet(int num) {
        return (num >> 8) == 0;
    }

    /** Returns true if 'c' is a digital char */
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /** Returns true if 'c' is a hex-letter char */
    private boolean isHexAtoF(char c) {
        return (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
}
