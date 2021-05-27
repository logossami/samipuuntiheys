package database;

import java.util.List;

import model.Balsalevy;

// Rajapinta JDBCBalsalevyDAO-luokan toteuttamiseen.
// getItem-metodi pidetään rajapinnassa jatkotoiminnallisuuksia ajatellen.

public interface BalsalevyDAO {
	
    public List<Balsalevy> getAllItems();

    public Balsalevy getItem(long id);

    public boolean addItem(Balsalevy newItem);

    public boolean removeItem(Balsalevy item);

}
