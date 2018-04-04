public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> lsIter;
    Iterator<Integer> iIter;
    public Vector2D(List<List<Integer>> vec2d) {
        lsIter = vec2d.iterator();
    }

    @Override
	public Integer next() {
        if(this.hasNext()) return iIter.next();
        else return null;
    }

    @Override
	public boolean hasNext() {
        while (lsIter.hasNext() && (iIter == null || !iIter.hasNext())) iIter = lsIter.next().iterator();
        return iIter != null && iIter.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */