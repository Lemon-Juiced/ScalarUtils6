
require 'json'

cardinal_directions = %w[north south west east]
speed_levels = %w[slow medium fast]

# Define correct orientation mapping for each direction and face
orientation_map = {
  'north' => {
    'down' => 'arrow_orientation_3',
    'east' => 'arrow_orientation_2',
    'north' => 'top',
    'particle' => 'arrow',
    'south' => 'bottom',
    'up' => 'arrow_orientation_1',
    'west' => 'arrow_orientation_4'
  },
  'south' => {
    'down' => 'arrow_orientation_1',
    'east' => 'arrow_orientation_4',
    'north' => 'bottom',
    'particle' => 'arrow',
    'south' => 'top',
    'up' => 'arrow_orientation_3',
    'west' => 'arrow_orientation_2'
  },
  'west' => {
    'down' => 'arrow_orientation_4',
    'east' => 'bottom',
    'north' => 'arrow_orientation_2',
    'particle' => 'arrow',
    'south' => 'arrow_orientation_4',
    'up' => 'arrow_orientation_4',
    'west' => 'top'
  },
  'east' => {
    'down' => 'arrow_orientation_2',
    'east' => 'top',
    'north' => 'arrow_orientation_4',
    'particle' => 'arrow',
    'south' => 'arrow_orientation_2',
    'up' => 'arrow_orientation_2',
    'west' => 'bottom'
  }
}

speed_levels.each do |speed|
  cardinal_directions.each do |direction|
    block_name = "#{speed}_conveyor_#{direction}"
    textures = {}
    orientation_map[direction].each do |face, orientation|
      textures[face] = "scalarutils:block/#{speed}_conveyor_#{orientation}"
    end
    json_content = {
      parent: "block/cube",
      textures: textures
    }
    File.write("#{block_name}.json", JSON.pretty_generate(json_content))
    puts "Generated #{block_name}.json"
  end
end