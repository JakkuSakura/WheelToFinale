package client.input;

import shared.events.Event;

public class KeyEvent extends Event {
    public enum Keys {
        A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
        N1, N2, N3, N4, N5, N6, N7, N8, N9, N0, UP, DOWN, LEFT, RIGHT, NONE, SPACE, LMB, RMB
    }

    private Keys key;

    public KeyEvent(Keys key) {
        this.key = key;
    }

    public Keys getKey() {
        return key;
    }
}
