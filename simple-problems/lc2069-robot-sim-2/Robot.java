/**
 * @author: orc-dev
 * @update: Jan.7 2024
 * 
 * Thoughts
 *   - Math: module calculation to found current pointer within the border
 *   - Math: threshold check
 *   - Edge-case: calling getDir() before calling step()
 *   - Lazy-mode: multiple calling of step() before calling getters
 * 
 * Performance
 *   - Runtime: %%
 *   - Memory: %%
 */
class Robot {
    // Private fields
    private final String[] DIR = {"East", "North", "West", "South"};
    private int[] thold;
    private int ptr = 0;
    private int d = 0;
    private int x = 0;
    private int y = 0;
    private boolean updated = true;
    
    // Constructor that create the thold Array.
    public Robot(int width, int height) {
        final int w = width - 1;
        final int h = height - 1;
        thold = new int[] {
            w,
            w + h,
            w + w + h,
            w + w + h + h};
    }
    
    // Clear 'updated' flag and compute 'ptr' by modulo (2w + 2h).
    public void step(int num) {
        this.updated = false;
        this.ptr = (this.ptr + num) % this.thold[3];
    }
    
    // Returns updated position.
    public int[] getPos() {
        if (!this.updated) {
            this.compute();
        }
        return new int[]{this.x, this.y};
    }

    // Computes the current position and direction as result of step().
    private void compute() {
        this.updated = true;
        int value = (this.ptr > 0) ? this.ptr : this.thold[3];
        
        for (int i = 0; i < 4; ++i) {
            if (value <= this.thold[i]) {
                this.x = this.getX(value, i);
                this.y = this.getY(value, i);
                this.d = i;
                break;
            }
        }
    }

    // Returns the current x-coordinates.
    private int getX(int value, int k) {
        return switch(k) {
            case 0 -> value;
            case 1 -> this.thold[0];
            case 2 -> this.thold[2] - value;
            case 3 -> 0;
            default -> 
                throw new IllegalArgumentException("Key error: " + k);
        };
    }

    // Returns the current y-coordinates.
    private int getY(int value, int k) {
        return switch(k) {
            case 0 -> 0;
            case 1 -> value - this.thold[0];
            case 2 -> this.thold[1] - this.thold[0];
            case 3 -> this.thold[3] - value;
            default -> 
                throw new IllegalArgumentException("Key error: " + k);
        };
    }
    
    // Returns the current direction as a String.
    public String getDir() {
        if (!this.updated) {
            this.compute();
        }
        return this.DIR[this.d];
    }
}