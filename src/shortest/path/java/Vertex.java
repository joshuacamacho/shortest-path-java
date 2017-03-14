package shortest.path.java;

/**
 *
 * @author Joshua
 */
public class Vertex {
  public String id;
  public City c;
  
  
  public Vertex(String id, City city) {
    this.id = id;
    this.c = city;
  }
  public String getId() {
    return id;
  }

  public String getName() {
    return c.getInfo();
  }
  

  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Vertex other = (Vertex) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return c.getName();
  }
  
}