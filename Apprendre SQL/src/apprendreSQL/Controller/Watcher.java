package apprendreSQL.Controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * This class watches for any change on .../resource and executes an action accordingly.
 *
 */
public class Watcher {

	private WatchService watcher;
	private Path directory;
	private EventManager manager;

	public Watcher(EventManager manager) {
		directory = Paths.get("resource/");
		this.manager = manager;
	}

	/**
	 * Watches for changes on the 'resource' directory.
	 */
	@SuppressWarnings("rawtypes")
	protected void observe() {
		try {
			this.watcher = FileSystems.getDefault().newWatchService();
			directory.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);

			boolean valid = true;
			do {
				WatchKey watchKey = watcher.take();

				for (WatchEvent event : watchKey.pollEvents()) {
					if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
						String fileName = event.context().toString();
						manager.updateExercisesView();
						System.out.println("File Created: " + fileName);
					}

					if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())) {
						String fileName = event.context().toString();
						manager.updateExercisesView();
						System.out.println("File Deleted: " + fileName);
					}

					if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())) {
						String fileName = event.context().toString();
						if (fileName.endsWith(".json"))
							manager.updateExercisesView();
						System.out.println("File Modified: " + fileName);
					}
				}
				valid = watchKey.reset();

			} while (valid);
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}

	}

}
