package llk;

import java.util.Arrays;

//方块数据
public class Fangkuai {

	private byte[] b;

	public Fangkuai(byte[] b) {
		this.b = b;
	}

	public byte[] getBytes() {
		return b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(b);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fangkuai other = (Fangkuai) obj;
		if (!Arrays.equals(b, other.b))
			return false;
		return true;
	}
	
}
