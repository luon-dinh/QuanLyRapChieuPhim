package plugin;

import javafx.beans.property.SimpleFloatProperty;

public class SimpleRangeFloatProperty extends SimpleFloatProperty {
	private float upperBound = Float.MAX_VALUE, lowerBound = Float.MIN_VALUE;

	public SimpleRangeFloatProperty() {
		super();
	}

	public SimpleRangeFloatProperty(float initialValue) {
		super(initialValue);
	}

	public SimpleRangeFloatProperty(float LowerBound, float UpperBound) {
		super();
		trySetRange(LowerBound, UpperBound);
	}

	public SimpleRangeFloatProperty(float initialValue, float LowerBound, float UpperBound) {
		super(initialValue);
		trySetRange(LowerBound, UpperBound);
	}

	public void setRange(float LowerBound, float UpperBound) throws Exception {
		if (LowerBound > UpperBound)
			throw new Exception("Cận trên phải lớn hơn cạnh dưới");
		lowerBound = LowerBound;
		upperBound = UpperBound;
	}

	public void trySetRange(float LowerBound, float UpperBound) {
		try {
			setRange(LowerBound, UpperBound);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void set(float newValue) {
		if (newValue < lowerBound)
			super.set(lowerBound);
		else if (newValue > upperBound)
			super.set(upperBound);
		else
			super.set(newValue);
	};

	@Override
	public void setValue(Number v) {
		set((float) v);
	}
}
