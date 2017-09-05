package zl.pattern.factory.factoryMethod;

public class PersonFactory extends Factory{

	@Override
	public <T extends Person> T getInstance(Class<T> c) {
		// TODO Auto-generated method stub
		T person = null;
		try{
			person = (T)Class.forName(c.getName()).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return person;
	}

}
